package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.model.Room;

import javax.transaction.Transactional;

@Transactional
public interface RoomRepository extends CrudRepository<Room, Long> {

    Room getAllByRoomId(long id);
}
