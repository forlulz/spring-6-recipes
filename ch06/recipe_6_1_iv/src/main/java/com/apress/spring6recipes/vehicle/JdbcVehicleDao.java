package com.apress.spring6recipes.vehicle;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcVehicleDao implements VehicleDao {

  private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?, ?, ?, ?)";
  private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
  private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
  private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
  private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";

  private final DataSource dataSource;

  public JdbcVehicleDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void insert(Vehicle vehicle) {
    var jdbcTemplate = new JdbcTemplate(this.dataSource);
    jdbcTemplate.update(INSERT_SQL, vehicle.getColor(), vehicle.getWheel(),
      vehicle.getSeat(), vehicle.getVehicleNo());
  }

  @Override
  public Vehicle findByVehicleNo(String vehicleNo) {
    try (var conn = dataSource.getConnection();
         var ps = conn.prepareStatement(SELECT_ONE_SQL)) {
      ps.setString(1, vehicleNo);

      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return toVehicle(rs);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public List<Vehicle> findAll() {
    try (var conn = dataSource.getConnection();
         var ps = conn.prepareStatement(SELECT_ALL_SQL);
         var rs = ps.executeQuery()) {

      var vehicles = new ArrayList<Vehicle>();
      while (rs.next()) {
        vehicles.add(toVehicle(rs));
      }
      return vehicles;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Vehicle vehicle) {
    try (var conn = dataSource.getConnection();
         var ps = conn.prepareStatement(UPDATE_SQL)) {
      prepareStatement(ps, vehicle);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(Vehicle vehicle) {
    try (var conn = dataSource.getConnection();
         var ps = conn.prepareStatement(DELETE_SQL)) {
      ps.setString(1, vehicle.getVehicleNo());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private Vehicle toVehicle(ResultSet rs) throws SQLException {
    return new Vehicle(rs.getString("VEHICLE_NO"), rs.getString("COLOR"), rs.getInt("WHEEL"), rs.getInt("SEAT"));
  }

  private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
    ps.setString(1, vehicle.getColor());
    ps.setInt(2, vehicle.getWheel());
    ps.setInt(3, vehicle.getSeat());
    ps.setString(4, vehicle.getVehicleNo());
  }
}
