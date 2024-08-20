package com.flow.main.entity;

import org.hibernate.annotations.Where;

import com.flow.main.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "alarms")
@Where(clause = "use_yn = true")
public class AlarmEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alarm_id")
	private Long alarmId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UsersEntity user;

	@Column(name = "alarm_type", nullable = false)
	private String alarmType;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "isRead", nullable = false)
	private Boolean isRead;

	@Column(name = "reference_id")
	private Long referenceId;

	@Column(name = "reference_table")
	private String referenceTable;

}
