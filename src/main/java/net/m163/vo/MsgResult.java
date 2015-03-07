package net.m163.vo;

public class MsgResult {

	private String id;
	private String richContent;
	private String shortContent;
	private String prettyTime;
	private int type;
	private int payType;
	private boolean isAdmin;
	private boolean isFromLoginUser;
	private String source;
	private String sourceLink;
	private User user;

	class User {
		private String id;
		private String nickName;
		private String url;
		private int age;
		private int sex;
		private String avatar;
		private boolean isAvatarAudit;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public boolean isAvatarAudit() {
			return isAvatarAudit;
		}

		public void setAvatarAudit(boolean isAvatarAudit) {
			this.isAvatarAudit = isAvatarAudit;
		}

	}

	private boolean isForbidden;
	private boolean isChartlet;
	private User toUser;
	private Object relatedData;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRichContent() {
		return richContent;
	}

	public void setRichContent(String richContent) {
		this.richContent = richContent;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getPrettyTime() {
		return prettyTime;
	}

	public void setPrettyTime(String prettyTime) {
		this.prettyTime = prettyTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isFromLoginUser() {
		return isFromLoginUser;
	}

	public void setFromLoginUser(boolean isFromLoginUser) {
		this.isFromLoginUser = isFromLoginUser;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isForbidden() {
		return isForbidden;
	}

	public void setForbidden(boolean isForbidden) {
		this.isForbidden = isForbidden;
	}

	public boolean isChartlet() {
		return isChartlet;
	}

	public void setChartlet(boolean isChartlet) {
		this.isChartlet = isChartlet;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Object getRelatedData() {
		return relatedData;
	}

	public void setRelatedData(Object relatedData) {
		this.relatedData = relatedData;
	}

}
