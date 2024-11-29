# Travel Rewards REST API

A Java-based REST API for managing travel rewards, built with Jakarta EE (JAX-RS) and JPA. This API provides complete CRUD operations for managing travel reward programs.

## Technologies Used
- Jakarta EE (JAX-RS) for REST endpoints
- JPA (Java Persistence API) for database persistence
- MariaDB as the database
- Gson for JSON serialization/deserialization
- CORS enabled for cross-origin requests

### Option 1: Using XAMPP (Recommended for Local Development)
1. Install XAMPP if not already installed
2. Start Apache and MySQL services in XAMPP Control Panel
3. Access phpMyAdmin at http://localhost/phpmyadmin
4. Create a new database named `travelexperts`
5. Import the SQL file from `src/main/resources/database/travelexperts.sql`

### Option 2: Using Standalone MariaDB
1. Install MariaDB Server from the official website
2. Create a new database named `travelexperts`
3. Import using command line:
   ```bash
   mysql -u root -p travelexperts < src/main/resources/database/travelexperts.sql
      ```

Note: This project uses default local development credentials. In a production environment, credentials would be properly secured using environment variables or secure configuration management.

## API Endpoints

### Get All Rewards
- **GET** `/api/rewards`
- Returns a list of all rewards in JSON format

### Get Specific Reward
- **GET** `/api/rewards/get/{rewardId}`
- Returns a single reward by ID

### Create New Reward
- **POST** `/api/rewards/post`
- Creates a new reward
- Requires JSON body with reward details

### Update Reward
- **PUT** `/api/rewards/put`
- Updates an existing reward
- Requires JSON body with updated reward details

### Delete Reward
- **DELETE** `/api/rewards/delete/{rewardId}`
- Deletes a reward by ID

## Sample JSON Format
```json
{
  "rwdName": "Gold Member",
  "rwdDesc": "Special rewards for gold members"
}
```

## Development Setup
1. Clone the repository
2. Set up the database using one of the options above
3. Configure database connection in `persistence.xml` if needed
    - Default configuration uses local development settings
    - For production, credentials should be externalized
4. Build and run the project using your preferred IDE

## Security Note
This project demonstrates API development in a local environment. In a production setting:
- Database credentials would be managed through environment variables
- CORS settings would be more restrictive
- Additional security measures like authentication would be implemented

## Cross-Origin Resource Sharing
This API includes CORS configuration to allow cross-origin requests, making it suitable for use with separate frontend applications (Web and Android clients).