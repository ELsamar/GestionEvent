<?php

namespace WebServiceBundle\Controller;

use AppBundle\Entity\Evenement;
use AppBundle\Entity\Participation;
use AppBundle\Entity\Review;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class EventController extends Controller
{
    public function indexAction()
    {
        return $this->render('WebServiceBundle:Default:index.html.twig');
    }

    public function allAction()
    {
        $event = $this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Evenement')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }

    public function findAction($id)
    {
        $event = $this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Evenement')
            ->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }

    public function newAction($id_user,$titre,$filename,$descrption,$type,$place,$dates,$heur,$lat,$long)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("AppBundle:User")
            ->find($id_user);
        $event = new Evenement();
        $event->setTitre($titre);
        $date=new \DateTime('now');
        $date->format('Ymd');
        $event->setDateajout(new \DateTime('now'));
        $event->setStart(new \DateTime('now'));
        $event->setNb(0);
        $event->setAffiche($filename);
        $event->setCreateur($user);
        $event->setEtat('en attente');
        $event->setRealise(1);
        $event->setVu(0);
        $event->setEtoile(0);
        $event->setDescription($descrption);
        $event->setCategorie($type);
        $event->setPlace($place);
       // $event->setHeure($heur);
        $event->setLongitude($long);
        $event->setLatitude($lat);

        $em->persist($event);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }
    public function updatAction($id_event,$titre,$filename,$descrption,$type,$place,$dates,$heur,$lat,$long)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")
            ->find($id_event);
        $event->setTitre($titre);
        $date=new \DateTime('now');
        $date->format('Ymd');
        $event->setDateajout(new \DateTime('now'));
        $event->setStart(new \DateTime('now'));
        $event->setNb(0);

        $event->setAffiche($filename);
        $event->setEtat('en attente');
        $event->setRealise(1);
        $event->setDescription($descrption);
        $event->setCategorie($type);
        $event->setPlace($place);
       // $event->setHeure($heur);
        $event->setLongitude($long);
        $event->setLatitude($lat);
        $em->persist($event);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }
    public function EventRecherchesAction($tag){
        $prods= $this->getDoctrine()->getManager()->getRepository('AppBundle:Evenement')
            ->FindByName($tag);
        $normalizer = new ObjectNormalizer() ;
        $normalizer->setCircularReferenceLimit(3);

        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted = $serializer->normalize($prods);


        return new JsonResponse($formatted);
    }
    public function EventParCategorieAction($cat){
        $prods= $this->getDoctrine()->getManager()->getRepository('AppBundle:Evenement')
            ->FindByType($cat);
        $normalizer = new ObjectNormalizer() ;
        $normalizer->setCircularReferenceLimit(3);

        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted = $serializer->normalize($prods);


        return new JsonResponse($formatted);
    }
    public function trieretoileEventAction(){
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")
            ->ORDERBYetoileevent();
        $normalizer = new ObjectNormalizer() ;
        $normalizer->setCircularReferenceLimit(3);

        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted = $serializer->normalize($event);


        return new JsonResponse($formatted);
    }


    public function usereventAction($id_user){
        $em = $this -> getDoctrine()-> getManager();
        $user = $em->getRepository("AppBundle:User")
            ->find($id_user);
        $event = $em -> getRepository("AppBundle:Evenement")
            ->FindMyEv($user);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }
    public function deleteAction($ide)
    {
        $test = " ";
        $em=$this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")->find($ide);
        $em->remove($event);
        $em->flush();
        $test="success";
         $serializer = new Serializer([new ObjectNormalizer()]);
         $formatted = $serializer->normalize($test);
         return new JsonResponse($formatted);
       // return "success";
    }
//*****************************particip******************************************/

    public function participateAction($id_event ,$id_user)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")
            ->find($id_event);
        $event->setNb($event->getNb()+1);
        $user = $em->getRepository("AppBundle:User")
            ->find($id_user);

        $participation = new Participation();
        $participation ->setParticipant($user);
        $participation ->setEvent($event);
        $participation ->setDatedeparti(new \DateTime('now'));
        $participation ->setType('participer');

        $em= $this->getDoctrine()->getManager();
        $em->persist($participation);
        $em->persist($event);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participation);
        return new JsonResponse($formatted);
    }

public  function findpartiAction($id_event,$id_user){
    $em = $this->getDoctrine()->getManager();
    $user = $em->getRepository("AppBundle:User")
        ->find($id_user);
    $event = $em->getRepository("AppBundle:Evenement")
        ->find($id_event);
    $participation = $em->getRepository("AppBundle:Participation")
        ->Findparti($user,$event);
    $serializer = new Serializer([new ObjectNormalizer()]);
    $formatted = $serializer->normalize($participation);
    return new JsonResponse($formatted);
}

    public function deletepartiAction($idp)
    {
        $test = " ";
        $em=$this->getDoctrine()->getManager();
        $participation = $em->getRepository("AppBundle:Participation")->find($idp);
        $em->remove($participation);
        $em->flush();
        $test="success";
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($test);
        return new JsonResponse($formatted);
        // return "success";
    }
    public function myparticipAction($id_user){

        $em = $this -> getDoctrine()-> getManager();
        $user = $em->getRepository("AppBundle:User")
            ->find($id_user);
        $participation = $em -> getRepository("AppBundle:Participation")
            ->findBy((array(
                'participant' => $user
            )));

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participation);
        return new JsonResponse($formatted);
    }
    public function acceppartiAction($idp)
    {
        $em = $this->getDoctrine()->getManager();
        $partipation = $em -> getRepository("AppBundle:Participation")->find($idp);
        //$modele de type objet
        $partipation->setType('participer');
        $em ->persist($partipation);
        $em ->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($partipation);
        return new JsonResponse($formatted);

    }
/***********************************REVIW*******************************************/
    public function allReviewAction($id_event)
    { $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")
            ->find($id_event);
        $review = $this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Review')
            ->FindReviwEvent($event);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($review);
        return new JsonResponse($formatted);
    }
    public function RatingAction($id_event ,$id_user,$comm,$note){

        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("AppBundle:Evenement")
            ->find($id_event);
        $user = $em->getRepository("AppBundle:User")
            ->find($id_user);
     $review = new Review();
     $review->setUser($user);
     $review->setCommentaire($comm);
     $review->setNote($note);
    $review->setEvent($event);
    $review->setDateajout(new \DateTime('now'));
    $event->setEtoile($this->calculerVote($event)
    );
    $em->persist($event);
    $em->persist($review);
    $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($review);
        return new JsonResponse($formatted);
}
    public function calculerVote(Evenement $event)
    {
        $em = $this->getDoctrine()->getManager();
        $x = 0.0;
        $rates = $em->getRepository('AppBundle:Review')->findBy(array('event' => $event));
        foreach ($rates as $rate) {
            $x = $x + $rate->getnote();
        }
        if (count($rates) > 0) {
            $x = $x / count($rates);
        } else {
            $x = 0;
        }
        return round($x);
    }
//**********************************User*********************************************/

    public function allUserAction()
    {
        $user = $this->getDoctrine()->getManager()->getRepository('AppBundle:User')->findAll();
        $normalizer = new ObjectNormalizer() ;


        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted = $serializer->normalize($user);


        return new JsonResponse($formatted);
    }
    public function authentificationAction(Request $request)
    {
        $valid = false;
        $em = $this->getDoctrine()->getManager();
        $json="";
        //$hash = password_hash(, PASSWORD_BCRYPT);
        $user = $em->getRepository('AppBundle:User')->findOneBy(array('username'=> $request->get('login')));
        //$user = $em->getRepository('WebServiceBundle:User')->findUserByloginQB( $request->get('login'));


        if (password_verify($request->get('password'),$user->getPassword() )){

            //  var_dump($hash);

            $valid = true;
            $encoders = array(new JsonEncoder());
            $normalizer = new ObjectNormalizer();

            $normalizer->setCircularReferenceLimit(2);

            $normalizer->setIgnoredAttributes(array("Rate","rateBonplan","rating"));

            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $normalizers = array($normalizer);
            $serializer = new \Symfony\Component\Serializer\Serializer($normalizers, $encoders);


            $jsonContent = $serializer->serialize($user, 'json');
            $json = json_decode($jsonContent);
            //   $json= JsonResponse($json);
        }
        else{
            $json="";
        }


        return new JsonResponse($json);
        /*var_dump($user);
        return new Response("56s4df5s");*/

    }
}