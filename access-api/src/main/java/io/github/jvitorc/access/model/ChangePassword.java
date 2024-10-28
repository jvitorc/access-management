package io.github.jvitorc.access.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "EN_CHANGE_PASSWORD")
public class ChangePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "change_password_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Column(name = "url_secret")
    private String urlSecret;

    @Column(name = "old_password")
    private String oldPassword;

    private Boolean status;

    @Column(name = "create_date")
    private Date createDate;


}
