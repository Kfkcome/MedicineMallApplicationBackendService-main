package pers.ervinse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private int Location_Id;
    private int StartTime;
    private int EndTime;
    private String Location;
}
