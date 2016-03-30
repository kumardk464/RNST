package com.ascendia.dheeraj.rnst.pojo;

public class RowItem {
	
	
	

	    private String title;
	    private int id;
	    private int icon;

	    public RowItem(String title,  int icon) {
	        this.title = title;
	        this.icon = icon;
            this.id =id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	   public int getIcon() {
	        return icon;
	    }

	    public void setIcon(int icon) {
	        this.icon = icon;
	    }
		
		public int getId(){
			return this.id;
		}

		// setting id
		public void setId(int id){
			this.id = id;
		}

	}


