public class Airbnb {
		String city;
		String ID;
		String hostID;
		String roomType;
		String price;
		String minNights;
		String rating;
		String availability;
		
		public Airbnb(String city, String ID, String hostID, String roomType, String price, String minNights) {
			this.city = city;
			this.ID = ID;
			this.hostID = hostID;
			this.roomType = roomType;
			this.price = price;
			this.minNights = minNights;
		}

		public String getCity() {
			return city;
		}

		public String getID() {
			return ID;
		}

		public String getHostID() {
			return hostID;
		}	
		
		public String getRoomType() {
			return roomType;
		}

		public String getPrice() {
			return price;
		}

		public String getMinNights() {
			return minNights;
		}	
	}
