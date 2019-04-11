package nl.han.ica.oose.dea.spotitube.dto;

import javax.inject.Inject;
import java.util.List;


public class PlaylistRequestDto {
  private int id;
  private String name;
  private boolean owner;




  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isOwner() {
    return owner;
  }

  public void setOwner(boolean owner) {
    this.owner = owner;
  }


}
