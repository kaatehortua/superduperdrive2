package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("Select * from credentials where userid=#{userid}")
    List<CredentialModel> getCredential(int userid);

    @Insert("insert into credentials values(#{url},#{username},#{key},#{pass},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean insertCredential(CredentialModel credential);

   @Update("update credentials set url= #{url}, key=#{key}, password=#{pass}" +
           "where credentialid=#{id}")
    boolean updateCredentia(CredentialModel credential);

   @Delete("delete from credentials where credentialid=#{id}")
    int deleteCredential(int credentialid);

   @Select("select * from credentials where credentialid=#{id]")
    CredentialModel getCredentialbyId(int credentialid);

}
