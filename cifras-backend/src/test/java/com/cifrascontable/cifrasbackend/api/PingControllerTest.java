package com.cifrascontable.cifrasbackend.api;

//@SpringBootTest
//public class PingControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PingController pingController;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders
//            .standaloneSetup(pingController)
//            .build();
//    }
//
//    @After
//    public void cleanup() {
//        Mockito.reset();
//    }
//
//    @Test
//    void pingControllerOKTest() throws Exception {
//        var response = mockMvc.perform(get("/ping"))
//                .andExpect(status().isOk())
//            .andReturn();
//
//        Assertions.assertTrue(response.getResponse().getContentAsString().equals("pong"));
//    }
//
//}
