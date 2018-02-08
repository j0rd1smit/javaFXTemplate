package nl.smit.scheduler.referee.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * An example entity.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@Table(name = "EXAMPLE")
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Example {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    @SuppressWarnings("nullness")
    private UUID id;


    @Column
    @Getter
    @Setter
    private String name = "";
}
