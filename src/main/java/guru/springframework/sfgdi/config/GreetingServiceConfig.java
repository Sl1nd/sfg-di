package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(SfgConstructorConfig.class)
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

  @Bean
  FakeDataSource fakeDataSource(SfgConstructorConfig sfgConstructorConfig){
    FakeDataSource fakeDataSource = new FakeDataSource();
    fakeDataSource.setJdbcurl(sfgConstructorConfig.getJdbcurl());
    fakeDataSource.setPassword(sfgConstructorConfig.getPassword());
    fakeDataSource.setUsername(sfgConstructorConfig.getUsername());
    return fakeDataSource;
  }
  @Bean
  PetServiceFactory petServiceFactory(){
    return new PetServiceFactory();
  }
  @Profile({"dog", "default"})
  @Bean
  PetService dogPetService(PetServiceFactory petServicefactory) {
     return petServicefactory.getPetService("dog");
  }

  @Profile("cat")
  @Bean
  PetService catPetService(PetServiceFactory petServicefactory) {
    return petServicefactory.getPetService("cat");
  }

  @Profile({"ES", "default"})
  @Bean("i18nService")
  I18NSpanishService i18NSpanishService() {
    return new I18NSpanishService();
  }

  @Bean
  EnglishGreetingRepository englishGreetingRepository(){
    return new EnglishGreetingRepositoryImpl();
  }

  @Profile("EN")
  @Bean("i18nService")
  I18nEnglishGreetingService i18nEnglishService(EnglishGreetingRepository englishGreetingRepository) {
    return new I18nEnglishGreetingService(englishGreetingRepository);
  }

  @Primary
  @Bean
  PrimaryGreetingService primaryGreetingService() {
    return new PrimaryGreetingService();
  }

  ConstructorGreetingService constructorGreetingService() {
    return new ConstructorGreetingService();
  }

  @Bean
  PropertyInjectedGreetingService propertyInjectedGreetingService(){
    return new PropertyInjectedGreetingService();
  }

  @Bean
  SetterInjectedGreetingService setterInjectedGreetingService(){
    return new SetterInjectedGreetingService();
  }
}
