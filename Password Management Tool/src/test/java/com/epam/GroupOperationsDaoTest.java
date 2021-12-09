package com.epam;

public class GroupOperationsDaoTest
{
//    @BeforeEach
//    void initiate()
//    {
//        PasswordOperations operate = new PasswordOperationsImpl();
//        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
//        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
//        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
//        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
//    }
//
//    @Test
//    @DisplayName(value = "Getting proper group name from User")
//    void getGroupNameTest1()
//    {
//        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        User user = MasterUsersOperationsDao.getUser("Manash").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 4"));
//        System.out.println(groupOperate.getGroup(user,2));
//        Assertions.assertTrue("Group 2".equals(groupOperate.getGroup(user,2)));
//    }
//
//    @Test
//    @DisplayName(value = "Getting group name from null User")
//    void getGroupNameTest2()
//    {
//        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        User user = MasterUsersOperationsDao.getUser("Manash").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 4"));
//        Assertions.assertFalse("Group 2".equals(groupOperate.getGroup(null,2)));
//    }
//
//    @Test
//    @DisplayName(value = "update group name of User")
//    void updateGroupNameTest1()
//    {
//        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        User user = MasterUsersOperationsDao.getUser("Manash").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 4"));
//        Assertions.assertTrue("Group 2".equals(groupOperate.getGroup(user,2)));
//        Assertions.assertTrue(groupOperate.updateGroupName(user,2, "new group"));
//        Assertions.assertTrue("new group".equals(groupOperate.getGroup(user,2)));
//    }
//
//    @Test
//    @DisplayName(value = "update group name of null User")
//    void updateGroupNameTest2()
//    {
//        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        User user = MasterUsersOperationsDao.getUser("Manash").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 4"));
//        Assertions.assertTrue("Group 2".equals(groupOperate.getGroup(user,2)));
//        Assertions.assertTrue(groupOperate.updateGroupName(user,2, "new group"));
//        Assertions.assertFalse("new group".equals(groupOperate.getGroup(null,98)));
//    }
}
