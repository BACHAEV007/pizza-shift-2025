package com.example.pizza_shift_2025.data.mapper

import com.example.pizza_shift_2025.data.dbentity.DoughEntity
import com.example.pizza_shift_2025.data.dbentity.PizzaEntity
import com.example.pizza_shift_2025.data.dbentity.SizeEntity
import com.example.pizza_shift_2025.data.dbentity.ToppingEntity
import com.example.pizza_shift_2025.data.model.DoughModel
import com.example.pizza_shift_2025.data.model.IngredientModel
import com.example.pizza_shift_2025.data.model.OrderedPizzaModel
import com.example.pizza_shift_2025.data.model.PaymentAddressModel
import com.example.pizza_shift_2025.data.model.PaymentDebitCardModel
import com.example.pizza_shift_2025.data.model.PaymentPersonModel
import com.example.pizza_shift_2025.data.model.PizzaModel
import com.example.pizza_shift_2025.data.model.PizzaPaymentModel
import com.example.pizza_shift_2025.data.model.SizeModel
import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Dough
import com.example.pizza_shift_2025.domain.entity.DoughType
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.IngredientType
import com.example.pizza_shift_2025.domain.entity.PaymentAddress
import com.example.pizza_shift_2025.domain.entity.PaymentDebitCard
import com.example.pizza_shift_2025.domain.entity.PaymentPerson
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.PizzaPayment
import com.example.pizza_shift_2025.domain.entity.Size
import com.example.pizza_shift_2025.domain.entity.SizeType
import java.util.UUID

class PizzaMapper {

    fun toPizzaEntity(pizza: BasketPizza): PizzaEntity {
        return PizzaEntity(
            id = UUID.randomUUID().toString(),
            name = pizza.name,
            price = pizza.price,
            img = pizza.img,
            description = pizza.description,
            toppings = pizza.toppings.map { toToppingEntity(it) },
            size = toSizeEntity(pizza.size),
            doughs = toDoughEntity(pizza.doughs)
        )
    }

    private fun toToppingEntity(ingredient: Ingredient): ToppingEntity {
        return ToppingEntity(
            name = ingredient.name.name,
            cost = ingredient.cost
        )
    }

    private fun toSizeEntity(size: Size): SizeEntity {
        return SizeEntity(
            name = size.name.name,
            price = size.price
        )
    }

    private fun toDoughEntity(dough: Dough): DoughEntity {
        return DoughEntity(
            name = dough.name.name,
            price = dough.price
        )
    }

    fun toPizza(pizzaEntity: PizzaEntity): BasketPizza {
        return BasketPizza(
            name = pizzaEntity.name,
            description = pizzaEntity.description,
            toppings = pizzaEntity.toppings.map { toTopping(it) },
            size = toSize(pizzaEntity.size),
            doughs = toDough(pizzaEntity.doughs),
            price = 0,
            img = pizzaEntity.img,

        )
    }

    private fun toTopping(toppingEntity: ToppingEntity): Ingredient {
        return Ingredient(
            name = enumValueOf<IngredientType>(toppingEntity.name),
            cost = toppingEntity.cost,
            img = ""
        )
    }

    private fun toSize(sizeEntity: SizeEntity): Size {
        return Size(
            name = enumValueOf<SizeType>(sizeEntity.name),
            price = sizeEntity.price
        )
    }

    private fun toDough(doughEntity: DoughEntity): Dough {
        return Dough(
            name = enumValueOf<DoughType>(doughEntity.name),
            price = doughEntity.price
        )
    }
    fun PizzaPayment.toPizzaPaymentModel(): PizzaPaymentModel {
        return PizzaPaymentModel(
            receiverAddress = this.receiverAddress.toPaymentAddressModel(),
            person = this.person.toPaymentPersonModel(),
            debitCard = this.debitCard.toPaymentDebitCardModel(),
            pizzas = this.pizzas.map { it.toPizzaModel() }
        )
    }

    fun PaymentAddress.toPaymentAddressModel(): PaymentAddressModel {
        return PaymentAddressModel(
            street = this.street,
            house = this.house,
            apartment = this.apartment,
            comment = this.comment
        )
    }

    fun PaymentPerson.toPaymentPersonModel(): PaymentPersonModel {
        return PaymentPersonModel(
            firstname = this.firstName,
            lastname = this.lastName,
            middlename = this.middlename,
            phone = this.phone
        )
    }

    fun PaymentDebitCard.toPaymentDebitCardModel(): PaymentDebitCardModel {
        return PaymentDebitCardModel(
            pan = this.pan,
            expireDate = this.expireDate,
            cvv = this.cvv
        )
    }

    fun BasketPizza.toPizzaModel(): OrderedPizzaModel {
        return OrderedPizzaModel(
            id = UUID.randomUUID().toString(),
            name = this.name,
            toppings = this.toppings.map { IngredientModel(name = it.name, cost = it.cost, img = it.img) },
            size = SizeEntity(name = this.size.name.toString(), price = this.size.price),
            doughs = DoughEntity(name = this.doughs.name.toString(), price = this.doughs.price)
        )
    }
}