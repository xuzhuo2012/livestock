package edu.hbut.livestock.util;

public enum ModuleId {
	
	FEED_MANAGE {

		@Override
		public String getModuleName() {
			return "饲料管理";
		}
		
	},
	
	HOUSE_MANAGE {

		@Override
		public String getModuleName() {
			return "圈舍管理";
		}
		
	},
	
	MEDICAL_MANAGE {

		@Override
		public String getModuleName() {
			return "医疗管理";
		}
		
	},
	
	USER_MANAGE {

		@Override
		public String getModuleName() {
			return "用户管理";
		}
		
	},
	
	BASE_DATA_MANAGE {

		@Override
		public String getModuleName() {
			return "基础数据管理";
		}
		
	},
	
	/** FEED_MANAGE---饲料管理  */
	FEEDING {

		@Override
		public String getModuleName() {
			return "饲料使用管理";
		}
		
	},

	FEED {

		@Override
		public String getModuleName() {
			return "饲料变动管理";
		}
		
	},
	
	/** HOUSE_MANAGE---圈舍管理  */
	HOUSE_INFO {

		@Override
		public String getModuleName() {
			return "圈舍信息管理 ";
		}
		
	},
	
	DEATH_PROCESSING {

		@Override
		public String getModuleName() {
			return "死亡处理";
		}
		
	},
	
	PRODUCING_RECORD {

		@Override
		public String getModuleName() {
			return "生产记录";
		}
		
	},
	
	LIVESTOCK {

		@Override
		public String getModuleName() {
			return "畜禽管理";
		}
		
	},
	
	/** MEDICAL_MANAGE---医疗管理  */
	MEDICINAL_RECORD {

		@Override
		public String getModuleName() {
			return "兽药使用记录";
		}
		
	},
	
	DISINFECT_RECORD {

		@Override
		public String getModuleName() {
			return "消毒记录";
		}
		
	},
	
	IMMUNIZATION {

		@Override
		public String getModuleName() {
			return "免疫记录";
		}
		
	},
	
	DIAGNOSIS_RECORD {
		
		@Override
		public String getModuleName() {
			return "诊疗记录";
		}
		
	},
	
	MONITOR_RECORD {
		
		@Override
		public String getModuleName() {
			return "监控记录";
		}
		
	},
	
	QUARANTINE_APPLY {

		@Override
		public String getModuleName() {
			return "检疫申报";
		}
		
	},
	
	QUARANTINE_PROGRESS {

		@Override
		public String getModuleName() {
			return "检疫处理";
		}
		
	},
	
	USER_INFO_MANAGE {

		@Override
		public String getModuleName() {
			return null;
		}
		
	},
	
	USER_PERMISSION {

		@Override
		public String getModuleName() {
			return "用户授权管理";
		}
		
	},
	
	GROUP_PERMISSION {

		@Override
		public String getModuleName() {
			return "角色授权管理";
		}
		
	},
	
	APPLY_CONTACT {

		@Override
		public String getModuleName() {
			return "申报联系人";
		}
		
	},
	
	FORBIDDEN_MEDICINE {

		@Override
		public String getModuleName() {
			return "禁用药品管理";
		}
		
	},
	
	DEPARTMENT_MANAGE {

		@Override
		public String getModuleName() {
			return "监控部门管理";
		}
		
	},
	
	MONITOR_ITEM_MANAGE {

		@Override
		public String getModuleName() {
			return "监控项目管理";
		}
		
	};
	
	public abstract String getModuleName();
	
	public static ModuleId fromName(String name) {
		for (ModuleId mId : values()) {
			if (mId.getModuleName().equals(name)) {
				return mId;
			}
		}
		return null;
	}
}
