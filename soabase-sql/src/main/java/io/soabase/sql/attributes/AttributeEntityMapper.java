package io.soabase.sql.attributes;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface AttributeEntityMapper
{
    @Select("SELECT * FROM SoaBaseAttributes")
    public List<AttributeEntity> selectAll();

    @Update("CREATE TABLE SoaBaseAttributes (fKEY VARCHAR(255) NOT NULL, fSCOPE VARCHAR(255) NOT NULL, fVALUE VARCHAR(65535), fTIMESTAMP VARCHAR(255) NOT NULL, PRIMARY KEY (fKEY, fSCOPE))")
    public int createDatabase();

    @Insert("INSERT INTO SoaBaseAttributes (fKEY, fSCOPE, fVALUE, fTIMESTAMP) VALUES (#{fKEY}, #{fSCOPE}, #{fVALUE}, #{fTIMESTAMP})")
    public int insert(AttributeEntity attribute);

    @Update("UPDATE SoaBaseAttributes SET fVALUE = #{fVALUE} WHERE fKEY = #{fKEY} AND fSCOPE = #{fSCOPE} AND fTIMESTAMP = #{fTIMESTAMP}")
    public int update(AttributeEntity attribute);
}
