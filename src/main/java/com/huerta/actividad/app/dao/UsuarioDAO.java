package com.huerta.actividad.app.dao;


import com.huerta.actividad.app.model.Persona;
import com.huerta.actividad.app.util.DBUtil;

import java.sql.*;

public class UsuarioDAO {

    public int guardarUsuario(Persona p, String passwordHash) throws SQLException {
        String sql = "INSERT INTO usuarios(nombre_completo, nombre_usuario, edad, sexo, estatura, password_hash) VALUES(?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombreCompleto());
            ps.setString(2, p.getNombreUsuario());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getSexo());
            ps.setDouble(5, p.getEstatura());
            ps.setString(6, passwordHash);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        }
    }

    public Persona findByUsername(String username) throws SQLException {
        String sql = "SELECT id_usuario, nombre_completo, nombre_usuario, edad, sexo, estatura, password_hash FROM usuarios WHERE nombre_usuario = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getInt("id_usuario"));
                    p.setNombreCompleto(rs.getString("nombre_completo"));
                    p.setNombreUsuario(rs.getString("nombre_usuario"));
                    p.setEdad(rs.getInt("edad"));
                    p.setSexo(rs.getString("sexo"));
                    p.setEstatura(rs.getDouble("estatura"));
                    return p;
                }
                return null;
            }
        }
    }

    public String getPasswordHashByUsername(String username) throws SQLException {
        String sql = "SELECT password_hash FROM usuarios WHERE nombre_usuario = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("password_hash");
                return null;
            }
        }
    }
}