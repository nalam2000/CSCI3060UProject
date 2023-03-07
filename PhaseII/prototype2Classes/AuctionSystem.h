#ifndef AUCTION_SYSTEM_H
#define AUCTION_SYSTEM_H

#include <string>
#include <unordered_map>
#include <vector>

class AuctionSystem {
public:
    AuctionSystem(const std::string& currentAccountsFile,
                  const std::string& availableItemsFile,
                  const std::string& transactionsFile);
    bool login(const std::string& username, const std::string& password);
    void displayTransactions() const;
    void createUser(const std::string& username, const std::string& password);
    void changePassword(const std::string& username, const std::string& password);
private:
    std::unordered_map<std::string, int> m_accounts;
    std::unordered_map<std::string, std::string> m_userAccounts;
    std::vector<std::string> m_items;
    void readAccounts(const std::string& filename);
    void writeAccounts(const std::string& filename) const;
    void readItems(const std::string& filename);
    void processTransaction(const std::string& transaction);
    void processTransactions(const std::string& filename);
};

#endif
