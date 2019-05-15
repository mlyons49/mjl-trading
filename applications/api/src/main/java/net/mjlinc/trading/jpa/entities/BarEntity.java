package net.mjlinc.trading.jpa.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "Bar")
@IdClass(BarKey.class)
public class BarEntity {
    @Id
    String s;
    @Id
    LocalDateTime t;
    @Id
    String i;

    BigDecimal o;
    BigDecimal c;
    BigDecimal h;
    BigDecimal l;
    Long v;
}
