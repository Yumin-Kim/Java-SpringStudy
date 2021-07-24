package jpastudy.stduy.repository.custom;

//TODO Member 엔티티에서 사용자명만 가지고 올 수 있게
public interface  UsernameOnly {
    String getUsername();

    TeamInfo getTeam();

    interface TeamInfo{
        String getName();
    }
}
