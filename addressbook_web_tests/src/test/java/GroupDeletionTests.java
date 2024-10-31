import org.junit.jupiter.api.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("group_name", "group_header", "group_footer");
        }
        deleteGroup();
    }

}
