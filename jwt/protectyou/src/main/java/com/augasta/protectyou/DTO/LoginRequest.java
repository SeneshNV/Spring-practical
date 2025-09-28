package com.augasta.protectyou.DTO;

import lombok.Data;

/*
@Getter → getters for all fields
@Setter → setters for all fields
@ToString → a nice toString() method
@EqualsAndHashCode → equals/hashCode methods
@RequiredArgsConstructor → constructor for final fields
 */

@Data
public class LoginRequest {
    private String username;
    private String role;
}
