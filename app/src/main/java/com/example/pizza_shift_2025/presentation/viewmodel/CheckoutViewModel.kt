package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.constants.Constants.PHONE_REGEX
import com.example.pizza_shift_2025.domain.entity.PaymentAddress
import com.example.pizza_shift_2025.domain.entity.PaymentDebitCard
import com.example.pizza_shift_2025.domain.entity.PaymentPerson
import com.example.pizza_shift_2025.domain.entity.PizzaPayment
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.OrderPizzaUseCase
import com.example.pizza_shift_2025.presentation.state.CheckoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class CheckoutViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val orderPizzaUseCase: OrderPizzaUseCase,
    private val countPizzaPriceUseCase: CountPizzaPriceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CheckoutState>(CheckoutState.FirstPart)
    val state: StateFlow<CheckoutState> = _state.asStateFlow()



    fun goToFirstPart() {
        _state.value = CheckoutState.FirstPart
    }

    fun validateFirstPart(paymentPerson: PaymentPerson, address: PaymentAddress) {
        val phoneRegex = Regex(PHONE_REGEX)
        if (!paymentPerson.phone.matches(phoneRegex)) {
            _state.value = CheckoutState.Failure("Номер телефона должен быть в формате 8XXXXXXXXXX")
        } else if (paymentPerson.firstName.isEmpty()) {
            _state.value = CheckoutState.Failure("Поле 'Имя' не заполнено")
        } else if (paymentPerson.lastName.isEmpty()) {
            _state.value = CheckoutState.Failure("Поле 'Фамилия' не заполнено")
        } else if (address.street.isEmpty() || address.house.isEmpty() || address.apartment.isEmpty()) {
            _state.value = CheckoutState.Failure("Недостаточно информации о адресе")
        } else {
            _state.value = CheckoutState.SecondPart
        }
    }
    fun validateDebitCard(paymentPerson: PaymentPerson, address: PaymentAddress, card: PaymentDebitCard){
        val cardPan = Regex("^\\d{4} \\d{4}$")
        val cardDate = Regex("^\\d{2}/\\d{2}$")
        val cardCVV = Regex("^\\d{3}$")

        if (!card.pan.matches(cardPan)) {
            _state.value = CheckoutState.Failure("Неправильно введён номер карты")
            return
        }

        if (!card.expireDate.matches(cardDate)) {
            _state.value = CheckoutState.Failure("Неправильно введён срок действия карты")
            return
        }

        if (!card.cvv.matches(cardCVV)) {
            _state.value = CheckoutState.Failure("Неправильно введён CVV код")
            return
        }
        _state.value = CheckoutState.Loading
        viewModelScope.launch {
            getBasketUseCase().collect { basket ->
                val pizzas = basket.map { it }

                val paymentData = PizzaPayment(
                    pizzas = pizzas,
                    person = paymentPerson,
                    receiverAddress = address,
                    debitCard = card
                )

                processPayment(paymentData)
            }
        }

    }

    private fun processPayment(payment: PizzaPayment) {
        viewModelScope.launch {
            _state.value = CheckoutState.Loading

            try {
                orderPizzaUseCase(payment)
                _state.value = CheckoutState.Success(
                    paymentPrice = payment.pizzas.sumOf { countPizzaPriceUseCase(it) }.toString(),
                    description = "кайф",//payment.pizzas.joinToString("\n") {
//                        makePizzaDescriptionUseCase(
//                            it
//                        )
//                    },
                    pizzaPayment = payment
                )
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = CheckoutState.Failure(ex.localizedMessage.orEmpty())
            }
        }
    }

}
