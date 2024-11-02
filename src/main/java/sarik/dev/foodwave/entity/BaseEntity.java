package sarik.dev.foodwave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
public abstract class BaseEntity {

    @Column(name = "is_deleted", nullable = false)
    protected boolean isDeleted = false;

    // umumiy maydonlar va metodlar shu yerda bo'lishi mumkin
}
