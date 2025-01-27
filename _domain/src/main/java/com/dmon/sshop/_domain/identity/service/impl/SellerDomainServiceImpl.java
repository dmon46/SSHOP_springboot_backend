package com.dmon.sshop._domain.identity.service.impl;

//@Service
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
//public class SellerDomainServiceImpl implements IAccessFactory {
//    ISellerDomainRepository sellerRepo;
//
//    IAccessMapper accessMapper;
//
//    AccountDomainServiceImpl accountService;
//
//    //FACTORY//
//    @Override
//    public AccountRes.Signup create(AccountReq.Create accountDto) {
//        Account accountCreated = this.accountService
//                .preparePreCreate(accountDto, Account.RoleType.SELLER);
//        Seller sellerCreated = Seller.builder()
//                .account(accountCreated)
//                .status(Seller.StatusType.REGISTERING.name())
//                .build();
//
//        Seller sellerResult = this.sellerRepo.save(sellerCreated);
//        return this.accessMapper.toRes(sellerResult);
//    }
//}
