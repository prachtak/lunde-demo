package cz.lundegaard.backend.mapper;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.model.form.ContactUsDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class FormMapperTest {

    @Test
    public void mapperTest() {
        FormMapper mapper = Mappers.getMapper(FormMapper.class);

        var cfp = new ContactFormType();
        cfp.setCode("CODE");
        cfp.setDescription("DESC");

        var dto = mapper.fromDomain(cfp);

        assertThat(dto.getCode()).isEqualTo("CODE");
        assertThat(dto.getDescription()).isEqualTo("DESC");

        var cud = new ContactUsDto();
        cud.setContactFormTypeCode("CODE");
        cud.setName("NAME");
        cud.setSurname("SURNAME");
        cud.setPolicyNumber("NUMBER");
        cud.setRequestMessage("MSG");

        var domain = mapper.toDomain(cud);

        assertThat(domain.getContactFormTypeCode()).isEqualTo("CODE");
        assertThat(domain.getName()).isEqualTo("NAME");
        assertThat(domain.getSurname()).isEqualTo("SURNAME");
        assertThat(domain.getPolicyNumber()).isEqualTo("NUMBER");
        assertThat(domain.getRequestMessage()).isEqualTo("MSG");
    }

}