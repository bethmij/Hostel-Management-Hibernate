package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<String> getRoomID() {
        return roomDAO.getRoomID();
    }

    @Override
    public List<String> getRoomType() {
        return roomDAO.getRoomType();
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty());
        return roomDAO.save(room);
    }

    @Override
    public List<RoomDTO> getRoomDetail() {
        List<Room> room = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();

        for (Room roomList:room) {
            RoomDTO roomDTO = new RoomDTO(roomList.getTypeId(),roomList.getType(),roomList.getKeyMoney(),roomList.getQty());
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    @Override
    public RoomDTO getRoom(String roomID) {
         Room room = roomDAO.search(roomID);
         return new RoomDTO(room.getTypeId(),room.getType(),room.getKeyMoney(),room.getQty());
    }

    @Override
    public boolean deleteRoom(String roomID) {
        return roomDAO.delete(roomID);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty());
        return roomDAO.update(room);
    }

    @Override
    public List<String> getReservebyRoomID(String roomID) {
        return queryDAO.getReserveIDbyRoomID(roomID);
    }

}
