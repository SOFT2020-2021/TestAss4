package dataLayer.performers;

import businessLayer.Bank;
import dataLayer.DAO;
import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class BankPerformer {

    public Bank get(String cvr) {
        Bank bank = null;
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("SELECT * FROM banks WHERE cvr = ?;");
            ps.setString(1, cvr);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bank = new MyBank(rs.getString(1), rs.getString(2), new HashMap<String, Account>());
            }
            rs.close();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bank;
    }

    public void delete(String cvr) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("DELETE FROM banks WHERE cvr = ?;");
            ps.setString(1, cvr);
            ps.executeUpdate();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void persist(String cvr, String name) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("INSERT INTO banks(cvr,name) VALUES(?, ?);");
            ps.setString(1, cvr);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(String oldCvr, String cvr, String name) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("UPDATE banks SET cvr=?, name=? WHERE cvr=?");
            ps.setString(1, cvr);
            ps.setString(2, name);
            ps.setString(3, oldCvr);
            ps.executeUpdate();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
