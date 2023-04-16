package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE noteId=#{id}")
    Note getNote(int NoteId);

    @Select("SELECT * FROM NOTES WHERE USERID=#{userid}")
    ArrayList<Note> getAllNotes(int userId);

    @Insert("INSERT INTO NOTES  (NOTETITLE ,NOTEDESCRIPTION , USERID ) VALUES(#{title},#{descr},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Note note);

    @Update("UPDATE NOTES SET NOTETITLE=#{title},NOTEDESCRIPTION=#{descr}")
    int update (Integer noteId,String title, String descr);

    @Delete("DELETE FROM NOTES WHERE NOTEID=#{id}")
    int delete(Integer noteid);
}
