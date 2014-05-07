package edu.hbut.livestock.util;

public enum ModuleId {
	
	FEED_MANAGE {

		@Override
		public String getModuleName() {
			return "���Ϲ���";
		}
		
	},
	
	HOUSE_MANAGE {

		@Override
		public String getModuleName() {
			return "Ȧ�����";
		}
		
	},
	
	MEDICAL_MANAGE {

		@Override
		public String getModuleName() {
			return "ҽ�ƹ���";
		}
		
	},
	
	USER_MANAGE {

		@Override
		public String getModuleName() {
			return "�û�����";
		}
		
	},
	
	BASE_DATA_MANAGE {

		@Override
		public String getModuleName() {
			return "�������ݹ���";
		}
		
	},
	
	/** FEED_MANAGE---���Ϲ���  */
	FEEDING {

		@Override
		public String getModuleName() {
			return "����ʹ�ù���";
		}
		
	},

	FEED {

		@Override
		public String getModuleName() {
			return "���ϱ䶯����";
		}
		
	},
	
	/** HOUSE_MANAGE---Ȧ�����  */
	HOUSE_INFO {

		@Override
		public String getModuleName() {
			return "Ȧ����Ϣ���� ";
		}
		
	},
	
	DEATH_PROCESSING {

		@Override
		public String getModuleName() {
			return "��������";
		}
		
	},
	
	PRODUCING_RECORD {

		@Override
		public String getModuleName() {
			return "������¼";
		}
		
	},
	
	LIVESTOCK {

		@Override
		public String getModuleName() {
			return "���ݹ���";
		}
		
	},
	
	/** MEDICAL_MANAGE---ҽ�ƹ���  */
	MEDICINAL_RECORD {

		@Override
		public String getModuleName() {
			return "��ҩʹ�ü�¼";
		}
		
	},
	
	DISINFECT_RECORD {

		@Override
		public String getModuleName() {
			return "������¼";
		}
		
	},
	
	IMMUNIZATION {

		@Override
		public String getModuleName() {
			return "���߼�¼";
		}
		
	},
	
	DIAGNOSIS_RECORD {
		
		@Override
		public String getModuleName() {
			return "���Ƽ�¼";
		}
		
	},
	
	MONITOR_RECORD {
		
		@Override
		public String getModuleName() {
			return "��ؼ�¼";
		}
		
	},
	
	QUARANTINE_APPLY {

		@Override
		public String getModuleName() {
			return "�����걨";
		}
		
	},
	
	QUARANTINE_PROGRESS {

		@Override
		public String getModuleName() {
			return "���ߴ���";
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
			return "�û���Ȩ����";
		}
		
	},
	
	GROUP_PERMISSION {

		@Override
		public String getModuleName() {
			return "��ɫ��Ȩ����";
		}
		
	},
	
	APPLY_CONTACT {

		@Override
		public String getModuleName() {
			return "�걨��ϵ��";
		}
		
	},
	
	FORBIDDEN_MEDICINE {

		@Override
		public String getModuleName() {
			return "����ҩƷ����";
		}
		
	},
	
	DEPARTMENT_MANAGE {

		@Override
		public String getModuleName() {
			return "��ز��Ź���";
		}
		
	},
	
	MONITOR_ITEM_MANAGE {

		@Override
		public String getModuleName() {
			return "�����Ŀ����";
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
