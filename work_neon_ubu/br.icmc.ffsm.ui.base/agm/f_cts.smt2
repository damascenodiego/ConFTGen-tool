(define-sort Feature () Bool)
(declare-const G Feature)
(declare-const A Feature)
(declare-const M Feature)
(declare-const L Feature)
(declare-const C Feature)
(declare-const R Feature)
(declare-const B Feature)
(declare-const N Feature)
(declare-const W Feature)
(declare-const V Feature)
(declare-const Y Feature)
(declare-const P Feature)
(declare-const S Feature)

(assert G)
(assert (and
   (= A G)
   (= M A)
   (= L A)
   (= C G)
   (= R G)
   (= (or B  N  W ) R)
   (not (and B N))
   (not (and B W))
   (not (and N W))
   (= V G)
   (= Y V)
   (= P V)
   (=> S V)
))

(push)
(assert (and true (not W) true))
(check-sat)
(pop)
(push)
(assert (and true S true))
(check-sat)
(pop)
(push)
(assert (and true (or (not W) S) true))
(check-sat)
(pop)
(push)
(assert (and true (and W (not S)) true))
(check-sat)
(pop)
(push)
(assert (and true true N))
(check-sat)
(pop)
(push)
(assert (and true true W))
(check-sat)
(pop)
(push)
(assert (and true true B))
(check-sat)
(pop)
(push)
(assert (and true W true))
(check-sat)
(pop)
(push)
(assert (and N true N))
(check-sat)
(pop)
(push)
(assert (and N true true))
(check-sat)
(pop)
(push)
(assert (and N true true))
(check-sat)
(pop)
(push)
(assert (and N S true))
(check-sat)
(pop)
(push)
(assert (and W true W))
(check-sat)
(pop)
(push)
(assert (and W (not S) W))
(check-sat)
(pop)
(push)
(assert (and W S S))
(check-sat)
(pop)
(push)
(assert (and W true true))
(check-sat)
(pop)
(push)
(assert (and W S true))
(check-sat)
(pop)
(push)
(assert (and B true B))
(check-sat)
(pop)
(push)
(assert (and B true B))
(check-sat)
(pop)
(push)
(assert (and B true true))
(check-sat)
(pop)
(push)
(assert (and B S true))
(check-sat)
(pop)
(push)
(assert (and true true true))
(check-sat)
(pop)
(push)
(assert (and true true true))
(check-sat)
(pop)
(push)
(assert (and true true N))
(check-sat)
(pop)
(push)
(assert (and true true W))
(check-sat)
(pop)
(push)
(assert (and true true B))
(check-sat)
(pop)
(push)
(assert (and true B S))
(check-sat)
(pop)
(push)
(assert (and true N S))
(check-sat)
(pop)
(push)
(assert (and S true S))
(check-sat)
(pop)
(push)
(assert (and S true S))
(check-sat)
(pop)
(push)
(assert (and S (not W) S))
(check-sat)
(pop)
(push)
(assert (and S W S))
(check-sat)
(pop)
(push)
(assert (and S true N))
(check-sat)
(pop)
(push)
(assert (and S true W))
(check-sat)
(pop)
(push)
(assert (and S true B))
(check-sat)
(pop)
