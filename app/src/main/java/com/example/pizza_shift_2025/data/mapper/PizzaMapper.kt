package com.example.pizza_shift_2025.data.mapper

import com.example.pizza_shift_2025.data.dbentity.DoughEntity
import com.example.pizza_shift_2025.data.dbentity.PizzaEntity
import com.example.pizza_shift_2025.data.dbentity.SizeEntity
import com.example.pizza_shift_2025.data.dbentity.ToppingEntity
import com.example.pizza_shift_2025.domain.entity.Dough
import com.example.pizza_shift_2025.domain.entity.DoughType
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.IngredientType
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.Size
import com.example.pizza_shift_2025.domain.entity.SizeType
import java.util.UUID

class PizzaMapper {

    fun toPizzaEntity(pizza: Pizza): PizzaEntity {
        return PizzaEntity(
            id = UUID.randomUUID().toString(),
            name = pizza.name,
            price = 0,
            img = pizza.img,
            description = pizza.description,
            toppings = pizza.toppings.map { toToppingEntity(it) },
            size = toSizeEntity(pizza.sizes.first()),
            doughs = toDoughEntity(pizza.doughs.first())
        )
    }

    private fun toToppingEntity(ingredient: Ingredient): ToppingEntity {
        return ToppingEntity(
            name = ingredient.name.name,
            cost = 0
        )
    }

    private fun toSizeEntity(size: Size): SizeEntity {
        return SizeEntity(
            name = size.name.name,
            price = 0
        )
    }

    private fun toDoughEntity(dough: Dough): DoughEntity {
        return DoughEntity(
            name = dough.name.name,
            price = 0
        )
    }

    fun toPizza(pizzaEntity: PizzaEntity): Pizza {
        return Pizza(
            id = pizzaEntity.id,
            name = pizzaEntity.name,
            description = pizzaEntity.description,
            toppings = pizzaEntity.toppings.map { toTopping(it) },
            sizes = listOf(toSize(pizzaEntity.size)),
            doughs = listOf(toDough(pizzaEntity.doughs)),
            allergens = emptyList(),
            calories = 0,
            carbohydrates = "0g",
            img = pizzaEntity.img,
            ingredients = emptyList(),
            isGlutenFree = false,
            isHit = false,
            isNew = false,
            isVegetarian = false,
            protein = "0g",
            sodium = "0mg",
            totalFat = "0g"
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
}