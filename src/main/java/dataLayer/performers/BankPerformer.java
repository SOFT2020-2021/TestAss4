package dataLayer.performers;

import businessLayer.Bank;
import dataLayer.DAO;
import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class BankPerformer {

    public Bank get(int cvr) {
        Bank bank = null;
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("SELECT * FROM banks WHERE cvr = ?;");
            ps.setInt(1, cvr);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bank = new MyBank(Integer.parseInt(rs.getString(1)), rs.getString(2), new HashMap<Integer, Account>());
            }
            rs.close();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bank;
    }

    public void delete(int cvr) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("DELETE FROM banks WHERE cvr = ?;");
            ps.setInt(1, cvr);
            ps.executeUpdate();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void persist(int cvr, String name) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("INSERT INTO banks(cvr,name) VALUES(?, ?);");
            ps.setInt(1, cvr);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(int oldCvr, int cvr, String name) {
        try {
            PreparedStatement ps = DAO.connection.prepareStatement("UPDATE banks SET cvr=?, name=? WHERE cvr=?");
            ps.setInt(1, cvr);
            ps.setString(2, name);
            ps.setInt(3, oldCvr);
            ps.executeUpdate();
            ps.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
