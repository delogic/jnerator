jNerator - The easiest way to generate test data for Java
========

What's jNerator ?
--------
It's a framework to generate Java POJOs based on simple configuration to easy the burden of starting new projects and to automate the task of creating data for testing.

How does it work ? 
--------
jNerator uses Reflection to read and write into objects attributes, dealing with collections, primitives, wrappers, arrays and complex types (a.k.a another objects). jNerator is not intrusive so there are no annotations to use neither classes to inherit. The configuration is simply creating the instance, preparing the generator and generating your values.

Examples and Samples
--------
You can use the our test cases as examples, but imaging you're building an application to manage a cafeteria. Then you need to generate data, probably you'd have objects like Product, Order, Client, Category, Additional, etc. Check below how these entities can be generated easily.

To start generating first instantiate the jNerator you want:

JNerator jNerator  = new JNeratorImpl();

Then prepare and generate!

List<YourType> objectsCreated = jNerator.prepare(YourType.class).generate(howManyYouWant);

And that's all you'll ever need to create all the classes below: (the classes can be found inside the testing packages).

//first we create the owner of the cafeteria
jNerator.prepare(Tenent.class).generate(amount));                     

//create the categories for the products
jNerator.prepare(Category.class).generate(amount));                   

//create the products
jNerator.prepare(Product.class).generate(amount));                    

//create additionals for products
jNerator.prepare(Additional.class).generate(amount));                 


Now you want to create orders, so you can simulate the workflow:

//create the instance generator for the orders
InstanceGenerator<Order> orderGenerator = jNerator.prepare(Order.class);

//configure how the relationship between order and delivery mode can be done
orderGenerator.setRelationshipAttributeGenerator("deliveryMode", HomeDelivered.class, StoreDelivered.class);

//configure how the relationship between order and payment mode can be done
orderGenerator.setRelationshipAttributeGenerator("paymentMode", CashPaymentMode.class, CreditCardPaymentMode.class);

//configure how the relationship between order and itemProducts can be done
InstanceGenerator<ItemProduct> itemProductsGenerators = orderGenerator
	.setRelationshipAttributeGenerator("orderItens", ItemProduct.class);
	
//configure how the relationship between item product and additional can be done
itemProductsGenerators.setRelationshipAttributeGenerator("additionals", AdditionalOrderItem.class);

//configure how the relationship between item product and product order item can be done
itemProductsGenerators.setRelationshipAttributeGenerator("products", ProductOrderItem.class);

//and that's it, now you have how many orders you might want!
List<Order> orders = orderGenerator.generate(amount);


Supported Types and Features
==========

Simple Attribute Types
----------
* Primitives [byte, short, int long, float, double, char, boolean]
* Wrappers [Byte, Short, Integer, Long, Float, Double, Character, Boolean]
* Special Numbers [BigInteger, BigDecimal]
* Others [String, java.util.Date]
* Enums

Collections\Arrays of Simple Attribute Types
-----------
* Collections [Collection, List, Set and subtypes]
* Arrays [uni-dimention]

Complex Types
-----------
* Any other object which can be created by Java Beans convention.

Collections\Arrays of Complex Attribute Types
-----------
* Collections [Collection, List, Set and subtypes]
* Arrays [uni-dimention]

Relationship Attribute Types - For bi-directional relationship objects
-----------
* One-to-one
* One-to-many
* Many-to-one
* Many-to-many

To configure how the relationship objects must be created register an attributeGenerator or relationshipAttributeGenerator for your instance generator.

If you need more information please contacts us celio@delogic.com.br