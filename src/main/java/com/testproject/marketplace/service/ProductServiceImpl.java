package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.product.ProductDTO;
import com.testproject.marketplace.dto.product.ProductOverviewDTO;
import com.testproject.marketplace.dto.product.ProductRequestDTO;
import com.testproject.marketplace.exception.ProductNotFoundException;
import com.testproject.marketplace.mapper.ProductDTOMapper;
import com.testproject.marketplace.mapper.ProductOverviewDTOMapper;
import com.testproject.marketplace.model.Product;
import com.testproject.marketplace.model.User;
import com.testproject.marketplace.repository.ProductPageRepository;
import com.testproject.marketplace.repository.ProductRepository;
import com.testproject.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductDTOMapper productDTOMapper;
    private final ProductOverviewDTOMapper productOverviewDTOMapper;
    private final ProductPageRepository productPageRepository;
    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    @Transactional
    public List<ProductOverviewDTO> findByPaginate(int pageNumber, int pageSize) {
        Pageable paginate = PageRequest.of(pageNumber, pageSize);
        Page<Product> pageResult = productPageRepository.findAll(paginate);
        return pageResult.map(productOverviewDTOMapper::mapper).toList();
    }

    @Override
    @Transactional
    public List<ProductOverviewDTO> findAllProducts() {
        final List<Product> products = productRepository.findAll();
        return products.stream().map(productOverviewDTOMapper::mapper).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createProductByUsername(ProductRequestDTO productRequestDTO, String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Product product = new Product();
        product.setUser(user);
        product.setTitle(productRequestDTO.getProductTitle());
        product.setDescription(productRequestDTO.getProductDescription());
        product.setPrice(productRequestDTO.getProductPrice());
        product.setLikes(0);
        product.setUnlikes(0);
        LOG.info("Saving Product for User {}", user.getId());
        productDTOMapper.mapper(product);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void editProductByUsername(Long id, ProductRequestDTO productRequestDTO, String username) {
        Product productEdit = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found by id " + id));
        if (!productEdit.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " is not allowed to update product with id " + id);
        }

        productEdit.setDescription(productRequestDTO.getProductDescription());
        productEdit.setTitle(productRequestDTO.getProductTitle());
        productEdit.setPrice(productRequestDTO.getProductPrice());
        LOG.info("Updating Product for User {}", " id = " + productEdit.getUser().getId());
        productRepository.save(productEdit);
        productDTOMapper.mapper(productEdit);
    }

    @Override
    @Transactional
    public void deleteProductByUsername(Long id, String username) {
        Product productDelete = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found by  id " + id));
        if (!productDelete.getUser().getUsername().equals(username)) {
            throw new EntityNotFoundException("User " + username + " not allowed to delete product with id " + id);
        }
        LOG.info("Deleted Product for User {}", " id = " + productDelete.getUser().getId());
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductDTO findProductByUsernameId(Long id) {
        return productRepository.findById(id).map(productDTOMapper::mapper)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " does not exist!"));
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllProductsByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("Products for user " + username + " not found"));
        final List<Product> products = productRepository.findAllByUser(user);
        return products.stream().map(productDTOMapper::mapper).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product likeProduct(Long productId, String username) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new ProductNotFoundException("Product cannot be found"));
        Optional<String> userLiked = product.getLikedUsers()
                .stream().filter(user->user.equals(username)).findAny();
        if(!product.getUser().getUsername().equals(username)){
            throw new EntityNotFoundException("User " + username + " not allowed to liked product with id " + productId);
        }
        if(userLiked.isPresent()){
            product.setLikes(product.getLikes() - 1);
            product.getLikedUsers().remove(username);
        } else {
            product.setLikes(product.getLikes() + 1);
            product.getLikedUsers().add(username);
        }
        return productRepository.save(product);
    }

    @Override
    public Product unlikeProduct(Long productId, String username) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new ProductNotFoundException("Product cannot be found"));
        Optional<String> userUnliked = product.getUnlikedUsers()
                .stream().filter(user->user.equals(username)).findAny();
        if(!product.getUser().getUsername().equals(username)){
            throw new EntityNotFoundException("User " + username + " not allowed to liked product with id " + productId);
        }
        if(userUnliked.isPresent()){
            product.setUnlikes(product.getUnlikes() - 1);
            product.getUnlikedUsers().remove(username);
        } else {
            product.setUnlikes(product.getUnlikes() + 1);
            product.getUnlikedUsers().add(username);
        }
        return productRepository.save(product);
    }

}
