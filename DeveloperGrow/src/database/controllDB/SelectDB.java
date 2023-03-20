package database.controllDB;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import database.dblist.CigaLog;
import database.dblist.Project;
import database.dblist.Rank;
import database.dblist.RankerInfo;
import database.dblist.SkillList;
import database.dblist.User;
import database.dblist.UserInfo;
import database.dblist.UserProject;
import database.dblist.UserSkill;
import database.util.ConnectionProvider;

public class SelectDB {
	public static List<CigaLog> selectCigaLog(int userId, int infoId) {
		List<CigaLog> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.cigaLog WHERE userId = ? AND infoId = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, infoId);
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int logId = rs.getInt("logId");
					String message = rs.getString("message");
					list.add(new CigaLog(logId, message, userId, infoId));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Project> selectProject() {
		List<Project> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.project";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String projectName = rs.getString("projectName");
					int rewardExp = rs.getInt("rewardExp");
					int time = rs.getInt("time");
					list.add(new Project(id, projectName, rewardExp, time));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Rank> selectRank() {
		List<Rank> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.rank ORDER BY score DESC LIMIT 10";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					String nickname = rs.getString("nickname");
					int score = rs.getInt("score");
					int userinfoId = rs.getInt("userinfoId");
					int userid = rs.getInt("userid");
					list.add(new Rank(nickname, score, userinfoId, userid));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	public static RankerInfo searchRankerInfo(int userInfoId, String userNickname) {
		
		String sql = "SELECT u.userNickname, ui.date, ui.time, ui.usedciga"
				+ " FROM team4.userinfo AS ui, team4.user AS u, team4.rank AS r"
				+ " WHERE ui.infoid = ? AND u.userNickname = ?"
				+ " ORDER BY score DESC LIMIT 1";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, userInfoId);
			stmt.setString(2, userNickname);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int date = rs.getInt("ui.date");
				int time = rs.getInt("ui.time");
				int usedCiga = rs.getInt("ui.usedciga");
				RankerInfo rankerInfo = new RankerInfo(date, time, usedCiga);
				return rankerInfo;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean searchUserRank(int userId) {
		String sql = "SELECT * FROM team4.rank WHERE userid = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setInt(1, userId);
				
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<SkillList> selectSkillList() {
		List<SkillList> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.skillList";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int skillId = rs.getInt("skillId");
					String skillName = rs.getString("skillName");
					int price = rs.getInt("price");
					String description = rs.getString("description");
					byte[] byteArr = rs.getBytes("image");
					ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
					BufferedImage image = ImageIO.read(bais);
					
					list.add(new SkillList(skillId, skillName, price, description, image));
				}
				return list;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	public static List<User> selectUser(int id) {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.user WHERE id = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					String userId = rs.getString("userId");
					String userPw = rs.getString("userPw");
					String nickname = rs.getString("usernickname");
					list.add(new User(id, userId, userPw, nickname));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<User> selectUser(String userId) {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM team4.user WHERE userId = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String userPw = rs.getString("userPw");
					String nickname = rs.getString("usernickname");
					list.add(new User(id, userId, userPw, nickname));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<UserInfo> selectUserinfo(int userId) {
		String sql = "SELECT * FROM team4.userinfo WHERE userid = ?";
		List<UserInfo> list = new ArrayList<>();
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {	
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int infoId = rs.getInt("infoId");
					int date = rs.getInt("date");
					int time = rs.getInt("time");
					int level = rs.getInt("level");
					int exp = rs.getInt("exp");
					int hp = rs.getInt("hp");
					int health = rs.getInt("health");
					int stress = rs.getInt("stress");
					int ciga = rs.getInt("ciga");
					int usedCiga = rs.getInt("usedCiga");
					boolean hide = rs.getBoolean("hide");
					boolean tory = rs.getBoolean("tory");
					boolean gameover = rs.getBoolean("gameover");
					list.add(new UserInfo(infoId, date, time, level, exp, hp, health, stress, ciga, usedCiga, hide, tory, gameover, userId));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static UserInfo searchNowGame(List<UserInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			if (! list.get(i).isGameover()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	public static List<UserProject> selectUserProject(int userId, int infoId) {
		List<UserProject> list = new ArrayList<>();
		String sql = "SELECT * FROM userProject WHERE userId = ? AND infoId = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, infoId);
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int projcetId = rs.getInt("projectId");
					boolean proceeding = rs.getBoolean("proceeding");
					boolean complete = rs.getBoolean("complete");
					int lastHour = rs.getInt("lastHour");
					int lastMin = rs.getInt("lastMin");
					int deadLine = rs.getInt("deadLine");
					list.add(new UserProject(id, userId, infoId, projcetId, proceeding, complete, lastHour, lastMin, deadLine));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<UserSkill> selectUserSkill(int userId, int infoId) {
		List<UserSkill> list = new ArrayList<>();
		String sql = "SELECT * FROM userSkill WHERE userId = ? AND infoId = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, infoId);
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int skillId = rs.getInt("skillId");
					int skillLevel = rs.getInt("skillLevel");
					list.add(new UserSkill(id, userId, infoId, skillId, skillLevel));
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
