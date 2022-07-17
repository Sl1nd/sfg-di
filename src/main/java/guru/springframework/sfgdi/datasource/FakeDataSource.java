package guru.springframework.sfgdi.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class FakeDataSource {
  private String username;
  private String password;
  private String jdbcurl;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getJdbcurl() {
    return jdbcurl;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setJdbcurl(String jdbcurl) {
    this.jdbcurl = jdbcurl;
  }

}
