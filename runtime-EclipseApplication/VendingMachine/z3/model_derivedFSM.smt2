(define-sort Feature () Bool)
(declare-const VM Feature)
(declare-const BEV Feature)
(declare-const COF Feature)
(declare-const TEA Feature)
(declare-const CAP Feature)
(declare-const TON Feature)
(declare-const CUR Feature)
(declare-const EUR Feature)
(declare-const DOL Feature)

(assert VM)
(assert (and
   (= BEV VM)
   (= (or COF  TEA  CAP ) BEV)
   (=> TON VM)
   (= CUR VM)
   (= (or EUR  DOL ) CUR)
   (not (and EUR DOL))
))


 (assert (and VM BEV COF CAP TON CUR DOL (not TEA) (not EUR) ))(assert (and true TON))
(assert (and 
    (not (and true true true TON ))
))
(check-sat)