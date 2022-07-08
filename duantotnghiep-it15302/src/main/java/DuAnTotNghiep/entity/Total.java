package DuAnTotNghiep.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Total implements Serializable{
	
	@Id
	String cuahang;
	String username;
	double tong;
	long soluong;
}
