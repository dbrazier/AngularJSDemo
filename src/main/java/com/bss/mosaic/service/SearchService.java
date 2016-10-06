package com.bss.mosaic.service;

import com.bss.mosaic.domain.*;
import com.bss.mosaic.repository.*;
import com.bss.mosaic.stars.domain.CaseInfo;
import com.bss.mosaic.stars.domain.CaseNote;
import com.bss.mosaic.stars.repository.CaseInfoRepository;
import com.bss.mosaic.stars.repository.CaseNoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Search.
 */
@Service
@Transactional
public class SearchService {

    private final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Inject
    private ClientAccountRepository clientAccountRepository;

    @Inject
    private RateRepository rateRepository;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private ClientParentRepository clientParentRepository;

    @Inject
    private InquiryInfoRepository inquiryInfoRepository;

    @Inject
    private InquiryTrailRepository inquiryTrailRepository;

    @Inject
    private PaymentFileRepository paymentFileRepository;

    @Inject
    private PaymentTransactionRepository paymentTransactionRepository;

    @Inject
    private CaseInfoRepository caseInfoRepository;

    @Inject
    private CaseNoteRepository caseNoteRepository;

    @Inject
    private ClientRiskRepository clientRiskRepository;


    public Optional<SearchResult> findByAccountNo(Long accountNo) {
        log.debug("Request to search by Account No: {}", accountNo);

        Optional<SearchResult> searchResult;

        //Search Stars for accountNo
        Optional<CaseInfo> caseInfo = caseInfoRepository.findByAccountNo(accountNo.toString());
        //Optional<ClientAccount> clientAccount = clientAccountRepository.findByAccountNo(accountNo);

        if (caseInfo.isPresent()) {
            SearchResult result = getSearchResult(caseInfo);
            searchResult = Optional.of(result);
        } else {
            searchResult = Optional.empty();
        }

        return searchResult;
    }


    public Optional<SearchResult> findByCaseId(String caseId) {
        log.debug("Request to search by CaseId: {}", caseId);

        Optional<SearchResult> searchResult;

        //Search Stars for caseId
        Optional<CaseInfo> caseInfo = caseInfoRepository.findByCaseId(caseId);

        if (caseInfo.isPresent()) {
            SearchResult result = getSearchResult(caseInfo);
            searchResult = Optional.of(result);
        } else {
            searchResult = Optional.empty();
        }

        return searchResult;
    }


    public Optional<SearchResult> findByGfcId(String gfcId) {
        log.debug("Request to search by GfcId: {}", gfcId);

        Optional<SearchResult> searchResult;

        //Search Stars for caseId
        Optional<CaseInfo> caseInfo = caseInfoRepository.findLatestByGfcId(gfcId);

        if (caseInfo.isPresent()) {
            SearchResult result = getSearchResult(caseInfo);
            searchResult = Optional.of(result);
        } else {
            searchResult = Optional.empty();
        }

        return searchResult;
    }


    private SearchResult getSearchResult(Optional<CaseInfo> caseInfo) {
        log.debug("Entering getSearchResult.");
        Date today = new Date();

        List<CaseInfo> caseInfoList = caseInfoRepository.findByGfcId(caseInfo.get().getGfcId());
        Optional<CaseNote> caseNote = caseNoteRepository.findByCaseKey(caseInfo.get().getPzinsKey());
        List<InquiryTrail> inquiryTrailList = inquiryTrailRepository.findByCaseIdOrderByInquiryDateDesc("S-160613-004801");
        List<PaymentFile> paymentFileList = paymentFileRepository.findByGfcId(Long.parseLong("1"));
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findByFileId(Long.parseLong("92308825"));
        Optional<ClientAccount> clientAccount = clientAccountRepository.findByAccountNo(Long.parseLong("12121212"));
        //For demo retrieve all Rates
        List<Rate> rateList = rateRepository.findAllByOrderByRateDateDesc();
        Optional<ClientRisk> clientRisk = clientRiskRepository.findByGfcId(Long.parseLong(caseInfo.get().getGfcId()));

//Old mocked data
//            Client client = clientRepository.findByGfcId(clientAccount.get().getGfcId());
//            ClientParent clientParent = clientParentRepository.findByGfpId(client.getGfcId());
//            //List<Rate> rateList = rateRepository.findByAccountNoOrderByRateDateDesc(accountNo);
//            List<InquiryInfo> inquiryInfoList = inquiryInfoRepository.findByGfcIdOrderByLastInteractionDesc(client.getGfcId());
//            String caseId = inquiryInfoList.get(0).getCaseId();
//            List<InquiryTrail> inquiryTrailList = inquiryTrailRepository.findByCaseIdOrderByInquiryDateDesc(caseId);
//            List<PaymentFile> paymentFileList = paymentFileRepository.findByGfcId(client.getGfpId());
//            List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findByFileId(paymentFileList.get(0).getFileId());
//            //add sla (days) to caseStartDate
//            inquiryInfoList.forEach(inquiryInfo -> inquiryInfo.setSlaEndDate(inquiryInfo.getCaseStartDate().plusDays(inquiryInfo.getSla())));

        SearchResult result = new SearchResult();
        result.setCaseInfo(caseInfo.get());

        //Has sla expired?
        caseInfoList.forEach(info -> {
            if (null != info.getSlaDeadline()) {
                info.setSlaExpired((info.getSlaDeadline().before(today)) ? true : false);
            } else {
                info.setSlaExpired(false);
            }
        });


        if (caseNote.isPresent())
            //if (StringUtils.isAsciiPrintable(caseNote.get().getNoteText()))
            result.setCaseNote(caseNote.get());
        result.setClientAccount(clientAccount.get());
        //result.setClient(client);
        //result.setClientParent(clientParent);
        result.setRateList(rateList);
        //result.setInquiryInfoList(inquiryInfoList);
        result.setCaseInfoList(caseInfoList);
        result.setInquiryTrailList(inquiryTrailList);
        result.setPaymentFileList(paymentFileList);
        result.setPaymentTransactionList(paymentTransactionList);
        if (clientRisk.isPresent())
            result.setClientRisk(clientRisk.get());

        return result;
    }




}
