
package serverModule.utility;

import common.data.Car;
import common.data.Coordinates;
import common.data.HumanBeing;
import common.data.WeaponType;
import common.data.Mood;
import common.exceptions.DatabaseManagerException;
import common.utility.HumanBeingLite;
import common.utility.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.TreeMap;

public class DatabaseCollectionManager {
    private final String SELECT_ALL_HUMAN_BEING = "SELECT * FROM " + DatabaseManager.HUMAN_TABLE;
    private final String SELECT_HUMAN_BEING_BY_ID = SELECT_ALL_HUMAN_BEING + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_HUMAN_BEING_BY_ID_AND_USER_ID = SELECT_HUMAN_BEING_BY_ID + " AND " +
            DatabaseManager.HUMAN_TABLE_USER_ID_COLUMN + " = ?";
    private final String INSERT_HUMAN_BEING = "INSERT INTO " +
            DatabaseManager.HUMAN_TABLE + " (" +
            DatabaseManager.HUMAN_TABLE_KEY_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_NAME_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_COORDINATES_ID_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_CREATION_DATE_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_REAL_HERO_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_HAS_TOOTHPICK_COLUMN+ ", " +
            DatabaseManager.HUMAN_TABLE_IMPACT_SPEED_COLUMN  + ", " +
            DatabaseManager.HUMAN_TABLE_WEAPON_TYPE_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_MOOD_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_CAR_ID_COLUMN + ", " +
            DatabaseManager.HUMAN_TABLE_USER_ID_COLUMN + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_HUMAN_BY_ID = "DELETE FROM " + DatabaseManager.HUMAN_TABLE +
            " WHERE " + DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_NAME_BY_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_REAL_HERO_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_REAL_HERO_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_HAS_TOOTHPICK_BY_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_HAS_TOOTHPICK_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_IMPACT_SPEED_BY_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_IMPACT_SPEED_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_WEAPON_TYPE_BY_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_WEAPON_TYPE_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_HUMAN_MOOD_BY_ID = "UPDATE " + DatabaseManager.HUMAN_TABLE + " SET " +
            DatabaseManager.HUMAN_TABLE_MOOD_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.HUMAN_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_ALL_COORDINATES = "SELECT * FROM " + DatabaseManager.COORDINATES_TABLE;
    private final String SELECT_COORDINATES_BY_ID = SELECT_ALL_COORDINATES + " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " =?";
    private final String DELETE_COORDINATES_BY_ID = "DELETE FROM " + DatabaseManager.COORDINATES_TABLE +
            " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_COORDINATES = "INSERT INTO " +
            DatabaseManager.COORDINATES_TABLE + " (" +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + ", " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + ") VALUES (?, ?)";
    private final String UPDATE_COORDINATES_BY_ID = "UPDATE " + DatabaseManager.COORDINATES_TABLE + " SET " +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + " = ?, " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_ALL_CARS = "SELECT * FROM " + DatabaseManager.CAR_TABLE;
    private final String SELECT_CAR_BY_ID = SELECT_ALL_CARS + " WHERE " + DatabaseManager.CAR_TABLE_NAME_COLUMN + " =?";
    private final String DELETE_CAR_BY_ID = "DELETE FROM " + DatabaseManager.CAR_TABLE +
            " WHERE " + DatabaseManager.CAR_TABLE_NAME_COLUMN + " = ?";
    private final String INSERT_CAR = "INSERT INTO " +
            DatabaseManager.CAR_TABLE + " (" +
            DatabaseManager.CAR_TABLE_NAME_COLUMN + ", " +
            DatabaseManager.CAR_TABLE_COOL_COLUMN + ") VALUES (?, ?)";
    private final String UPDATE_CAR_BY_ID = "UPDATE " + DatabaseManager.CAR_TABLE + " SET " +
            DatabaseManager.CAR_TABLE_NAME_COLUMN + " = ?, " +
            DatabaseManager.CAR_TABLE_COOL_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.CAR_TABLE_ID_COLUMN + " = ?";
    private DatabaseManager databaseManager;
    private DatabaseUserManager databaseUserManager;

    public DatabaseCollectionManager(DatabaseManager databaseManager, DatabaseUserManager databaseUserManager) {
        this.databaseManager = databaseManager;
        this.databaseUserManager = databaseUserManager;
    }

    private HumanBeing returnHumanBeing(ResultSet resultSet, long id) throws SQLException{
        String name = resultSet.getString(DatabaseManager.HUMAN_TABLE_NAME_COLUMN);
        int key = resultSet.getInt(DatabaseManager.HUMAN_TABLE_KEY_COLUMN);
        Coordinates coordinates = getCoordinatesByID(resultSet.getInt(DatabaseManager.HUMAN_TABLE_COORDINATES_ID_COLUMN));
        LocalDate creationDate = resultSet.getTimestamp(DatabaseManager.HUMAN_TABLE_CREATION_DATE_COLUMN).toLocalDateTime().toLocalDate();
        boolean realHero = resultSet.getBoolean(DatabaseManager.HUMAN_TABLE_REAL_HERO_COLUMN);
        Boolean hasToothpick = resultSet.getBoolean(DatabaseManager.HUMAN_TABLE_HAS_TOOTHPICK_COLUMN);
        float impactSpeed = resultSet.getFloat(DatabaseManager.HUMAN_TABLE_IMPACT_SPEED_COLUMN);
        WeaponType weaponType = WeaponType.valueOf(resultSet.getString(DatabaseManager.HUMAN_TABLE_WEAPON_TYPE_COLUMN));
        Mood mood = Mood.valueOf(resultSet.getString(DatabaseManager.HUMAN_TABLE_MOOD_COLUMN));
        Car car = getCarByID(resultSet.getString(DatabaseManager.HUMAN_TABLE_CAR_ID_COLUMN));
        User owner = databaseUserManager.getUserById(resultSet.getInt(DatabaseManager.HUMAN_TABLE_USER_ID_COLUMN));
        return new HumanBeing(id, key, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood,  car, owner );
    }

    public TreeMap<Integer, HumanBeing> getCollection() {
        TreeMap<Integer, HumanBeing> humans = new TreeMap<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ALL_HUMAN_BEING, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(DatabaseManager.HUMAN_TABLE_ID_COLUMN);
                int key = resultSet.getInt(DatabaseManager.HUMAN_TABLE_KEY_COLUMN);
                humans.put(key, returnHumanBeing(resultSet, id));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return humans;
    }

    private Coordinates getCoordinatesByID(int id) throws SQLException {
        Coordinates coordinates;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_COORDINATES_BY_ID, false);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coordinates = new Coordinates(
                        resultSet.getFloat(DatabaseManager.COORDINATES_TABLE_X_COLUMN),
                        resultSet.getFloat(DatabaseManager.COORDINATES_TABLE_Y_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_COORDINATES_BY_ID!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return coordinates;
    }

    private Car getCarByID(String name) throws SQLException {
        Car car;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_CAR_BY_ID, false);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = new Car(
                        resultSet.getString(DatabaseManager.CAR_TABLE_NAME_COLUMN),
                        resultSet.getBoolean(DatabaseManager.CAR_TABLE_COOL_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_CAR_BY_ID!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return car;
    }

    private String getCarIdByHumanBeingID(long HumanBeingID) throws SQLException {
        String carID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_HUMAN_BEING_BY_ID, false);
            preparedStatement.setLong(1, HumanBeingID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                carID = resultSet.getString(DatabaseManager.HUMAN_TABLE_CAR_ID_COLUMN);
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_HUMAN_BEING_BY_ID!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return carID;
    }

    private int getCoordinatesIdByHumanBeingID(long HumanBeingID) throws SQLException {
        int coordinatesID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_HUMAN_BEING_BY_ID, false);
            preparedStatement.setLong(1, HumanBeingID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coordinatesID = resultSet.getInt(DatabaseManager.HUMAN_TABLE_COORDINATES_ID_COLUMN);
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_HUMAN_BEING_BY_ID!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return coordinatesID;
    }

    public HumanBeing insertHumanBeing(HumanBeingLite humanLite, User user, int key) throws DatabaseManagerException {
        HumanBeing humanToInsert;
        PreparedStatement insertHuman = null;
        PreparedStatement insertCoordinates = null;
        PreparedStatement insertCar = null;
        try {
            databaseManager.setCommit();
            databaseManager.setSavepoint();

            LocalDate localDate = LocalDate.now();

            insertCar = databaseManager.doPreparedStatement(INSERT_CAR, true);
            insertCar.setString(1, humanLite.getCar().getName());
            insertCar.setBoolean(2, humanLite.getCar().getCool());
            if (insertCar.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetCar = insertCar.getGeneratedKeys();
            String carName;
            if (resultSetCar.next()) carName = resultSetCar.getString(1);
            else throw new SQLException();

            insertCoordinates = databaseManager.doPreparedStatement(INSERT_COORDINATES, true);
            insertCoordinates.setDouble(1, humanLite.getCoordinates().getX());
            insertCoordinates.setFloat(2, humanLite.getCoordinates().getY());
            if (insertCoordinates.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetCoordinates = insertCoordinates.getGeneratedKeys();
            int coordinatesID;
            if (resultSetCoordinates.next()) coordinatesID = resultSetCoordinates.getInt(1);
            else throw new SQLException();

            insertHuman = databaseManager.doPreparedStatement(INSERT_HUMAN_BEING, true);
            insertHuman.setInt(1, key);
            insertHuman.setString(2, humanLite.getName());
            insertHuman.setInt(3, coordinatesID);
            insertHuman.setTimestamp(4, Timestamp.valueOf(localDate.atStartOfDay()));
            insertHuman.setBoolean(5, humanLite.getRealHero());
            insertHuman.setBoolean(6, humanLite.getHasToothpick());
            insertHuman.setFloat(7, humanLite.getImpactSpeed());
            insertHuman.setString(8, humanLite.getWeaponType().toString());
            insertHuman.setString(9, humanLite.getMood().toString());
            insertHuman.setString(10, carName);
            insertHuman.setInt(11, databaseUserManager.getUserIdByUsername(user));
            if (insertHuman.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetHuman = insertHuman.getGeneratedKeys();
            long HumanBeingID;
            if (resultSetHuman.next()) HumanBeingID = resultSetHuman.getLong(1);
            else throw new SQLException();
            humanToInsert = new HumanBeing(
                    HumanBeingID,
                    key,
                    humanLite.getName(),
                    humanLite.getCoordinates(),
                    localDate,
                    humanLite.getRealHero(),
                    humanLite.getHasToothpick(),
                    humanLite.getImpactSpeed(),
                    humanLite.getWeaponType(),
                    humanLite.getMood(),
                    humanLite.getCar(),
                    user
            );
            databaseManager.commit();
            return humanToInsert;
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при добавлении нового объекта в БД!");
            exception.printStackTrace();
            databaseManager.rollback();
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(insertCar);
            databaseManager.closePreparedStatement(insertCoordinates);
            databaseManager.closePreparedStatement(insertHuman);
            databaseManager.setAutoCommit();
        }
    }

    public void updateHumanBeingByID(long HumanBeingID, HumanBeingLite humanLite) throws DatabaseManagerException {
        PreparedStatement updateHumanBeingName = null;
        PreparedStatement updateHumanBeingCoordinates = null;
        PreparedStatement updateHumanBeingRealHero = null;
        PreparedStatement updateHumanBeingHasToothpick = null;
        PreparedStatement updateHumanBeingImpactSpeed = null;
        PreparedStatement updateHumanBeingWeaponType = null;
        PreparedStatement updateHumanBeingMood = null;
        PreparedStatement updateHumanBeingCar = null;
        try {
            databaseManager.setCommit();
            databaseManager.setSavepoint();

            updateHumanBeingName = databaseManager.doPreparedStatement(UPDATE_HUMAN_NAME_BY_ID, false);
            updateHumanBeingCoordinates = databaseManager.doPreparedStatement(UPDATE_COORDINATES_BY_ID, false);
            updateHumanBeingRealHero = databaseManager.doPreparedStatement(UPDATE_HUMAN_REAL_HERO_ID, false);
            updateHumanBeingHasToothpick = databaseManager.doPreparedStatement(UPDATE_HUMAN_HAS_TOOTHPICK_BY_ID, false);
            updateHumanBeingImpactSpeed = databaseManager.doPreparedStatement(UPDATE_HUMAN_IMPACT_SPEED_BY_ID, false);
            updateHumanBeingWeaponType = databaseManager.doPreparedStatement(UPDATE_HUMAN_WEAPON_TYPE_BY_ID, false);
            updateHumanBeingMood = databaseManager.doPreparedStatement(UPDATE_HUMAN_MOOD_BY_ID, false);
            updateHumanBeingCar = databaseManager.doPreparedStatement(UPDATE_CAR_BY_ID, false);

            if (humanLite.getName() != null) {
                updateHumanBeingName.setString(1, humanLite.getName());
                updateHumanBeingName.setLong(2, HumanBeingID);
                if (updateHumanBeingName.executeUpdate() == 0) throw new SQLException();
            }
            if (humanLite.getCoordinates() != null) {
                updateHumanBeingCoordinates.setDouble(1, humanLite.getCoordinates().getX());
                updateHumanBeingCoordinates.setFloat(2, humanLite.getCoordinates().getY());
                updateHumanBeingCoordinates.setInt(3, getCoordinatesIdByHumanBeingID(HumanBeingID));
                if (updateHumanBeingCoordinates.executeUpdate() == 0) throw new SQLException();
            }

            updateHumanBeingRealHero.setBoolean(1, humanLite.getRealHero());
            updateHumanBeingRealHero.setLong(2, HumanBeingID);
            if (updateHumanBeingRealHero.executeUpdate() == 0) throw new SQLException();

            if (humanLite.getHasToothpick() != null) {
                updateHumanBeingHasToothpick.setBoolean(1, humanLite.getHasToothpick());
                updateHumanBeingHasToothpick.setLong(2, HumanBeingID);
                if (updateHumanBeingHasToothpick.executeUpdate() == 0) throw new SQLException();
            }
            if (humanLite.getImpactSpeed() != 0.0f) {
                updateHumanBeingImpactSpeed.setFloat(1, humanLite.getImpactSpeed());
                updateHumanBeingImpactSpeed.setLong(2, HumanBeingID);
                if (updateHumanBeingImpactSpeed.executeUpdate() == 0) throw new SQLException();
            }
            if (humanLite.getWeaponType() != null) {
                updateHumanBeingWeaponType.setString(1, humanLite.getWeaponType().toString());
                updateHumanBeingWeaponType.setLong(2, HumanBeingID);
                if (updateHumanBeingWeaponType.executeUpdate() == 0) throw new SQLException();
            }
            if (humanLite.getMood() != null) {
                updateHumanBeingMood.setString(1, humanLite.getMood().toString());
                updateHumanBeingMood.setLong(2, HumanBeingID);
                if (updateHumanBeingMood.executeUpdate() == 0) throw new SQLException();
            }
            if (humanLite.getCar() != null) {
                updateHumanBeingCar.setString(1, humanLite.getCar().getName());
                updateHumanBeingCar.setBoolean(2, humanLite.getCar().getCool());
                updateHumanBeingCar.setString(3, getCarIdByHumanBeingID(HumanBeingID));
                if (updateHumanBeingCar.executeUpdate() == 0) throw new SQLException();
            }
            databaseManager.commit();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении группы запросов на обновление объекта!");
            databaseManager.rollback();
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(updateHumanBeingName);
            databaseManager.closePreparedStatement(updateHumanBeingCoordinates);
            databaseManager.closePreparedStatement(updateHumanBeingRealHero);
            databaseManager.closePreparedStatement(updateHumanBeingHasToothpick);
            databaseManager.closePreparedStatement(updateHumanBeingImpactSpeed);
            databaseManager.closePreparedStatement(updateHumanBeingWeaponType);
            databaseManager.closePreparedStatement(updateHumanBeingCar);
            databaseManager.setAutoCommit();
        }
    }

    public boolean checkHumanBeingByIdAndUserId(long HumanBeingID, User user) throws DatabaseManagerException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_HUMAN_BEING_BY_ID_AND_USER_ID, false);
            preparedStatement.setLong(1, HumanBeingID);
            preparedStatement.setInt(2, databaseUserManager.getUserIdByUsername(user));
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_HUMAN_BEING_BY_ID_AND_USER_ID!");
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
    }

    public void deleteHumanBeingById(long HumanBeingID) throws DatabaseManagerException {
        PreparedStatement deleteHumanBeing = null;
        PreparedStatement deleteCoordinates = null;
        PreparedStatement deleteCar = null;
        try {
            int coordinatesID = getCoordinatesIdByHumanBeingID(HumanBeingID);
            String carID = getCarIdByHumanBeingID(HumanBeingID);
            deleteHumanBeing = databaseManager.doPreparedStatement(DELETE_HUMAN_BY_ID, false);
            deleteHumanBeing.setLong(1, HumanBeingID);
            if (deleteHumanBeing.executeUpdate() == 0) throw new SQLException();
            deleteCoordinates = databaseManager.doPreparedStatement(DELETE_COORDINATES_BY_ID, false);
            deleteCoordinates.setInt(1, coordinatesID);
            if (deleteCoordinates.executeUpdate() == 0) throw new SQLException();
            deleteCar = databaseManager.doPreparedStatement(DELETE_CAR_BY_ID, false);
            deleteCar.setString(1, carID);
            if (deleteCar.executeUpdate() == 0) throw new SQLException();
        } catch (SQLException exception) {
            exception.printStackTrace();
           // System.out.println("Произошла ошибка при выполнении запроса DELETE_HUMAN_BY_ID!");
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(deleteHumanBeing);
            databaseManager.closePreparedStatement(deleteCoordinates);
            databaseManager.closePreparedStatement(deleteCar);
        }
    }

    public void clearCollection() throws DatabaseManagerException{
        TreeMap<Integer, HumanBeing> humans = getCollection();
        for (HumanBeing human : humans.values()) {
            deleteHumanBeingById(human.getId());
        }
    }
}