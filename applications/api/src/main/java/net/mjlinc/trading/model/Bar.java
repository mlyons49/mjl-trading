package net.mjlinc.trading.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Bar {
    Long t;
    BigDecimal o;
    BigDecimal h;
    BigDecimal l;
    BigDecimal c;
    Long v;
    
    public String toString() {
        return  "{t:'"+t+
                "',o:'"+o+
                "',h:'"+h+
                "',l:'"+l+
                "',c:'"+c+
                "',v:'"+v+"'}";
    }
}
