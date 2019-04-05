package top.starrysea.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.starrysea.common.DaoResult;
import top.starrysea.dao.IUserDao;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.object.dto.User;

import static top.starrysea.common.Common.*;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public User saveUserDao(User user) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("user_id", user.getUserId()).insert("user_email", user.getUserEmail())
				.insert("user_password", sha512(user.getUserEmail() + user.getUserPassword()))
				.insert("user_name", user.getUsername()).insert("user_osu_person", user.getOsuPerson())
				.insert("user_osu_team", user.getOsuTeam()).insert("user_osu_grade", user.getOsuGrade())
				.insert("user_osu_group", user.getOsuGroup()).insert("user_dd_flag", user.getIsDD()).table(User.class)
				.end();
		return user;
	}

	@Override
	public DaoResult getUserDao(User user) {
		kumaSqlDao.selectMode();
		ListSqlResult<String> userEmail = kumaSqlDao.select("1").from(User.class)
				.where("user_email", WhereType.EQUALS, user.getUserEmail()).endForList(String.class);
		if (userEmail.getResult().isEmpty()) {
			return new DaoResult(false, "用户账号不存在");
		}
		ListSqlResult<User> userResult = kumaSqlDao.select("user_id").select("user_name").from(User.class)
				.where("user_email", WhereType.EQUALS, user.getUserEmail())
				.where("user_password", WhereType.EQUALS, sha512(user.getUserEmail() + user.getUserPassword()))
				.endForList((rs, row) -> new User.Builder().userId(rs.getString("user_id"))
						.username(rs.getString("user_name")).build());
		if (isNotNull(userResult.getResult())) {
			User result = userResult.getResult().get(0);
			return new DaoResult(true, result);
		} else {
			return new DaoResult(false, "密码错误");
		}
	}

	@Override
	public boolean checkUserAvailabilityDao(User user) {
		kumaSqlDao.selectMode();
		ListSqlResult<String> list = kumaSqlDao.select("1").from(User.class)
				.where("user_email", WhereType.EQUALS, user.getUserEmail()).endForList(String.class);
		return list.getResult().isEmpty();
	}

	@Override
	public User getUserInfoDao(String userId) {
		kumaSqlDao.selectMode();
		EntitySqlResult<User> result = kumaSqlDao.select("user_email").select("user_name").select("user_osu_person")
				.select("user_osu_team").select("user_osu_grade").select("user_osu_group").select("user_dd_flag")
				.from(User.class).where("user_id", WhereType.EQUALS, userId)
				.endForObject((rs, row) -> new User.Builder().userEmail(rs.getString("user_email"))
						.username(rs.getString("user_name")).osuPerson(rs.getShort("user_osu_person"))
						.osuTeam(rs.getShort("user_osu_team")).osuGrade(rs.getShort("user_osu_grade"))
						.osuGroup(rs.getShort("user_osu_group")).isDD(rs.getShort("user_dd_flag")).build());
		return result.getResult();
	}
}
