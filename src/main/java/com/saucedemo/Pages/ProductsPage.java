package com.saucedemo.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.AbstractComponents.AbstractComponents;

public class ProductsPage extends AbstractComponents {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='shopping_cart_link']")
	WebElement cartIcon;

	@FindBy(css = "[class='shopping_cart_badge']")
	WebElement cartIconCount;

	@FindBy(css = ".btn_inventory")
	WebElement addRemoveProduct;

	@FindBy(css = ".inventory_item_description")
	List<WebElement> productsName;

	@FindBy(css = ".inventory_details_back_button")
	WebElement backToProduct;

	@FindBy(css = ".btn_inventory")
	WebElement removeProduct;

	@FindBy(css = ".product_sort_container")
	WebElement productSort;

	By BY_cartCount = By.cssSelector("[class='shopping_cart_badge']");
	By BY_addToCart = By.cssSelector(".pricebar button[class*='btn_inventory']");
	By BY_selectProduct = By.cssSelector(".inventory_item_name");

	// Add product to cart by clicking on the 'Add to Cart' button.
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_addToCart).click();
		Thread.sleep(1000);
	}

	// Add product to cart from the product detail page (PDP).
	public void addToCartFromPDP(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_selectProduct).click();
		addRemoveProduct.click();
		Thread.sleep(1000);
	}

	// Check if items are present in cart and return the count.
	public int hasItemInCart() throws InterruptedException {
		boolean hasItemsInCart = driver.findElements(BY_cartCount).size() > 0;

		if (hasItemsInCart) {
			String numItemsInCart = driver.findElement(BY_cartCount).getText();
			return Integer.parseInt(numItemsInCart);

		} else {
			return 0;
		}
	}

	// Remove product from the product detail page (PDP).
	public void removeProductFromPDP(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_selectProduct).click();
		addRemoveProduct.click();
		Thread.sleep(1000);
	}

	// Remove product from product list page (PLP).
	public void removeProduct(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_addToCart).click();
		Thread.sleep(1000);
	}

	// Navigate to product list page (PLP) by clicking Back to products button.
	public void backToProducts() {
		backToProduct.click();
	}
	
	// Sorting products by select options.
	public void selectSortDropdown(String optionText) {
        Select select = new Select(productSort);
        select.selectByVisibleText(optionText);	
    }
	
	// Get product name list.
    public List<String> getProductList() {
        List<String> productNames = getProductsListFromPLP().stream()
                .map(product -> product.findElement(By.cssSelector("a")).getText())
                .collect(Collectors.toList());

        //productNames.forEach(System.out::println);
        return productNames;
    }
    
    // Check products are sorted by name in ascending order.
    public boolean areProductsSortedAscending() {
        List<String> originalProductNames = getProductList(); // Get product names before sorting

        List<String> sortedProductNames = originalProductNames.stream()
                .sorted()
                .collect(Collectors.toList());

        return originalProductNames.equals(sortedProductNames);
    }

    // Check products are sorted by name in descending order.
    public boolean areProductsSortedDescending() {
        List<String> originalProductNames = getProductList(); // Get product names before sorting

        List<String> sortedProductNames = originalProductNames.stream()
                .sorted((s1, s2) -> s2.compareToIgnoreCase(s1)) // Sorting in descending order
                .collect(Collectors.toList());

        return originalProductNames.equals(sortedProductNames);
    }
    
	// Get product prices list.
    public List<Double> getPriceList() {
        List<Double> prices = getPricesListFromPLP().stream()
                .map(product -> {
                    String priceText = product.findElement(By.cssSelector("[class='inventory_item_price']")).getText();

                    return Double.parseDouble(priceText.replaceAll("\\$", ""));
                })
                .collect(Collectors.toList());

        //prices.forEach(System.out::println);
        return prices;
    }
    
    // Check product prices are sorted in ascending order
	public boolean arePricesSortedAscending() {
		List<Double> originalPrices = getPriceList();

        List<Double> sortedPrices = originalPrices.stream()
                .sorted()
                .collect(Collectors.toList());

        return originalPrices.equals(sortedPrices);
	}
	
	// Check product prices are sorted in descending order
    public boolean arePricesSortedDescending() {
        List<Double> originalPrices = getPriceList();

        List<Double> sortedPrices = originalPrices.stream()
                .sorted((price1, price2) -> Double.compare(price2, price1)) // Sorting in descending order
                .collect(Collectors.toList());

        return originalPrices.equals(sortedPrices);
    }
}
