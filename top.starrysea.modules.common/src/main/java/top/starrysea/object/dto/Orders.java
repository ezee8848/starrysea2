package top.starrysea.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.object.view.out.OrderForAll;
import top.starrysea.object.view.out.OrderForOne;

public class Orders implements Entity {

	private String orderId;
	private String orderNum;
	private String orderName;
	private Area orderArea;
	private String orderAddress;
	private short orderStatus;
	private String orderExpressnum;
	private long orderTime;
	private String orderEMail;
	private String orderRemark;
	private String orderPhone;
	private User user;
	private int orderMoney;

	private Orders(Builder builder) {
		this.orderId = builder.orderId;
		this.orderNum = builder.orderNum;
		this.orderName = builder.orderName;
		this.orderArea = builder.orderArea;
		this.orderAddress = builder.orderAddress;
		this.orderStatus = builder.orderStatus;
		this.orderExpressnum = builder.orderExpressnum;
		this.orderTime = builder.orderTime;
		this.orderEMail = builder.orderEMail;
		this.orderRemark = builder.orderRemark;
		this.orderPhone = builder.orderPhone;
		this.user = builder.user;
		this.orderMoney = builder.orderMoney;
	}

	public Orders() {

	}

	public static class Builder implements IBuilder<Orders> {

		private String orderId;
		private String orderNum;
		private String orderName;
		private Area orderArea;
		private String orderAddress;
		private short orderStatus;
		private String orderExpressnum;
		private long orderTime;
		private String orderEMail;
		private String orderRemark;
		private String orderPhone;
		private User user;
		private int orderMoney;

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder orderNum(String orderNum) {
			this.orderNum = orderNum;
			return this;
		}

		public Builder orderName(String orderName) {
			this.orderName = orderName;
			return this;
		}

		public Builder orderArea(Area area) {
			this.orderArea = area;
			return this;
		}

		public Builder orderAddress(String orderAddress) {
			this.orderAddress = orderAddress;
			return this;
		}

		public Builder orderStatus(short orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder orderExpressnum(String orderExpressnum) {
			this.orderExpressnum = orderExpressnum;
			return this;
		}

		public Builder orderTime(long orderTime) {
			this.orderTime = orderTime;
			return this;
		}

		public Builder orderEMail(String orderMail) {
			this.orderEMail = orderMail;
			return this;
		}

		public Builder orderRemark(String orderRemark) {
			this.orderRemark = orderRemark;
			return this;
		}

		public Builder orderPhone(String orderPhone) {
			this.orderPhone = orderPhone;
			return this;
		}

		public Builder userId(String userId) {
			this.user = new User.Builder().userId(userId).build();
			return this;
		}

		public Builder orderMoney(int orderMoney) {
			this.orderMoney = orderMoney;
			return this;
		}

		@Override
		public Orders build() {
			return new Orders(this);
		}

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Area getOrderArea() {
		return orderArea;
	}

	public void setOrderArea(Area orderArea) {
		this.orderArea = orderArea;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public void setOrderExpressnum(String orderExpressnum) {
		this.orderExpressnum = orderExpressnum;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderEMail() {
		return orderEMail;
	}

	public void setOrderEMail(String orderEMail) {
		this.orderEMail = orderEMail;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(int orderMoney) {
		this.orderMoney = orderMoney;
	}

	public OrderForAll toVoForAll() {
		String status = "";
		if (this.orderStatus == (short) 0) {
			status = "未支付";
		} else if (this.orderStatus == (short) 1) {
			status = "未发货";
		} else if (this.orderStatus == (short) 2) {
			status = "已发货";
		} else if (this.orderStatus == (short) 3) {
			status = "已取消";
		}
		return new OrderForAll(orderId, orderNum, orderName, status, orderTime, orderMoney);
	}

	public OrderForOne toVoForOne() {
		return new OrderForOne(this);
	}
}
