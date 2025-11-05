package com.huerta.actividad.app.dao;

import com.huerta.actividad.app.model.IMCDTO;
import com.huerta.actividad.app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicionDAO {

    public void guardarMedicion(int idUsuario, double peso, double imc, String categoria) throws SQLException {
        String sql = "INSERT INTO mediciones_imc(id_usuario, peso, imc, categoria) VALUES(?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setDouble(2, peso);
            ps.setDouble(3, imc);
            ps.setString(4, categoria);
            ps.executeUpdate();
        }
    }

    public List<IMCDTO> obtenerHistorial(int idUsuario) throws SQLException {
        String sql = "SELECT peso, imc, categoria, fecha_medicion FROM mediciones_imc WHERE id_usuario = ? ORDER BY fecha_medicion DESC";
        List<IMCDTO> lista = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IMCDTO dto = new IMCDTO();
                    dto.setPeso(rs.getDouble("peso"));
                    dto.setImc(rs.getDouble("imc"));
                    dto.setCategoria(rs.getString("categoria"));
                    dto.setFechaMedicion(rs.getTimestamp("fecha_medicion").toString());
                    lista.add(dto);
                }
            }
        }
        return lista;
    }
}