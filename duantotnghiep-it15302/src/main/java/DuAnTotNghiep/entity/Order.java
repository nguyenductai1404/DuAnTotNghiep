package DuAnTotNghiep.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Orders")
public class Order  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String address;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Createdate")
	Date createDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	
	String nguoinhan;
	String sdt;
	String diachinn;
	String trangthai;
	String tongtien;
	boolean hoanthanh;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<Orderdetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<prepay> prepay;
	
	@ManyToOne
	@JoinColumn(name = "Codeid")
	Codesale codesale;
	
	@ManyToOne
	@JoinColumn(name = "Chid")
	Store cuahang;
}