package DuAnTotNghiep.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reportdetail implements Serializable{
	
	@Id
	String name;
	Date date;
	double doanhThu;
	long soLuong;
}
