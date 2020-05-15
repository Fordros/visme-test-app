package com.visme.app.model.request;


import com.visme.app.data.domain.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {

    /**
     * Id
     */
    Long id;

    /**
     * Title
     */
    @NotBlank
    private String title;

    /**
     * Type
     */
    @NotNull
    private ProjectType type;

    /**
     * Favorite state
     */
    private Boolean favorite = false;
}
