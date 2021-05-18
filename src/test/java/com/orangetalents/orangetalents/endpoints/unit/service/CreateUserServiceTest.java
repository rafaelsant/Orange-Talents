package com.orangetalents.orangetalents.endpoints.unit.service;

import com.orangetalents.orangetalents.DTO.BetDTO;
import com.orangetalents.orangetalents.Generator;
import com.orangetalents.orangetalents.Implementation.BetServImpl;
import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Models.Params;
import com.orangetalents.orangetalents.Repository.BetRepository;
import com.orangetalents.orangetalents.Repository.ParamsRepository;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CreateUserServiceTest {

	@Mock
	private ParamsRepository paramsRepository;
	@Mock
	private BetRepository betRepository;
	@InjectMocks
	private BetServImpl service;

	@Before
	public void init(){
		String email = "email@test.com";
		Params params = new Params(6,59);
		BetDTO bet = new BetDTO();

		Bet mockBet = new Bet();
		mockBet.setId(1L);
		mockBet.setEmail(email);
		mockBet.setBetNumbers(Generator.NumGenerator(mockBet,params.getBET_MAX_QUANTITY(),params.getBET_MAX_NUMBER()));

		List<Bet> resp = new ArrayList<Bet>();
		resp.add(mockBet);

		when(paramsRepository.getParams()).thenReturn(Optional.of(params));
	}

	@Test
	public void whenSaveBet_shouldReturnBetMaxQuantityNumbers() {
		String email = "email@test.com";
		assertThat(service.createBet(email).getNumerosGerados().size()).isEqualTo(paramsRepository.getParams().get().getBET_MAX_QUANTITY());
	}
	@Test
	public void whenSaveBet_ShowReturnNumbersMaxValue(){
		String email = "email@test.com";
		Set<Integer> response = service.createBet(email).getNumerosGerados();
		Integer maxValue = paramsRepository.getParams().get().getBET_MAX_NUMBER();
		response.forEach(res->assertThat(res <= maxValue).isEqualTo(true));
	}
	@Test
	public void whenSaveBet_ShowReturnDifferentValues(){
		String email = "email@test.com";
		Integer execTimes = 100;
		List<Set<Integer>> responseList = new ArrayList<Set<Integer>>();
		for(var i=0;i<execTimes;i++){
			Set<Integer> response = service.createBet(email).getNumerosGerados();
			responseList.add(response);
		}

		var j=0;
		while(j<execTimes){
			for(var k=j+1;k<=execTimes-1;k++){
				assertThat(responseList.get(j).equals(responseList.get(k))).isEqualTo(false);
			}
			j++;
		}
	}
}
