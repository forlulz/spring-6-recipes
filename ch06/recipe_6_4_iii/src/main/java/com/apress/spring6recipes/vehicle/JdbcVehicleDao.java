package com.apress.spring6recipes.vehicle;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class JdbcVehicleDao extends NamedParameterJdbcDaoSupport implements VehicleDao {

  @Override
  public void insert(final Vehicle vehicle) {
    String sql = "INSERT INTO VEHICLE (VEHICLE_NO, COLOR, WHEEL, SEAT) VALUES (:vehicleNo, :color, :wheel, :seat)";
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehicle);
    getNamedParameterJdbcTemplate().update(sql, parameterSource);
  }

  @Override
  public Vehicle findByVehicleNo(String vehicleNo) {
    String sql = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
    return getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(Vehicle.class), vehicleNo);
  }

  @Override
  public void update(Vehicle vehicle) {
    String sql = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
    getJdbcTemplate().update(sql, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(),
      vehicle.getVehicleNo());
  }

  @Override
  public void delete(Vehicle vehicle) {
    String sql = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";
    getJdbcTemplate().update(sql, vehicle.getVehicleNo());
  }

  @Override
  public List<Vehicle> findAll() {
    String sql = "SELECT * FROM VEHICLE";
    return getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(Vehicle.class));
  }

}
