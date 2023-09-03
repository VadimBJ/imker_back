package de.imker.InitializationData;

import de.imker.models.AboutUs;
import de.imker.models.Member;
import de.imker.repositories.AboutUsRepository;
import de.imker.repositories.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MembersUsInitialisation {

  @Autowired
  private final MembersRepository membersRepository;

  public void MemberInit() {
    List<Member> memberList = membersRepository.findAll();

    if (memberList.size() == 0) {
      Member newMember = Member.builder()
          .state(Member.State.SHOW)
          .name("Maik Jäger")
          .position("1 Vorsitzender")
          .description("Er st ein herausragender Imker, der die höchste Position in einem Imkerverband oder einer Imkervereinigung innehat. Als 1 Vorsitzender leitet er die Organisation, koordiniert die Arbeit der Imker und fördert die Entwicklung der Bienenzucht in Deutschland.")
          .image("151")
          .phone("+49 5162901266")
          .facebook("https://www.facebook.com/")
          .instagram("https://www.instagram.com/")
          .email("Imkerverein-Ahlden@t-online.de")
          .build();
      membersRepository.save(newMember);
    }
    if (memberList.size() == 0) {
      Member newMember = Member.builder()
          .state(Member.State.SHOW)
          .name("Peter Förster")
          .position("2 Vorsitzender")
          .description("Ein erfahrener Imker kümmert sich liebevoll um seine Bienen und trägt dazu bei, die Bestäubung von Pflanzen zu fördern. Seine Leidenschaft für die Bienenzucht ist unermüdlich und sein Wissen ist von unschätzbarem Wert für die Natur und die Landwirtschaft.")
          .image("152")
          .phone("+ 49 ")
          .facebook("https://www.facebook.com/")
          .instagram("https://www.instagram.com/")
          .email("Imkerverein-Ahlden@t-online.de")
          .build();
      membersRepository.save(newMember);
    }
    if (memberList.size() == 0) {
      Member newMember = Member.builder()
          .state(Member.State.SHOW)
          .name("Vanessa Arndt")
          .position("Kassenwartin")
          .description("Die Kassenwartin ist eine engagierte Imkerin, die für die finanziellen Angelegenheiten eines Imkervereins verantwortlich ist. Sie sorgt für eine solide Haushaltsführung und gewährleistet die finanzielle Stabilität der Imkergemeinschaft.")
          .image("156")
          .phone("+ 49 ")
          .facebook("https://www.facebook.com/")
          .instagram("https://www.instagram.com/")
          .email("Imkerverein-Ahlden@t-online.de")
          .build();
      membersRepository.save(newMember);
    }
    if (memberList.size() == 0) {
      Member newMember = Member.builder()
          .state(Member.State.SHOW)
          .name("Bärbel Grube")
          .position("Schriftführerin")
          .description("Die Schriftführerin ist eine engagierte Imkerin, die für die Dokumentation und Aufzeichnung der Aktivitäten eines Imkervereins verantwortlich ist. Sie führt Protokolle, verwaltet die Korrespondenz und trägt zur organisatorischen Effizienz des Vereins bei.")
          .image("153")
          .phone("+ 49 ")
          .facebook("https://www.facebook.com/")
          .instagram("https://www.instagram.com/")
          .email("Imkerverein-Ahlden@t-online.de")
          .build();
      membersRepository.save(newMember);
    }
    if (memberList.size() == 0) {
      Member newMember = Member.builder()
          .state(Member.State.SHOW)
          .name("Heinz Timrott")
          .position("Wanderwart")
          .description("Der Wanderwart ist ein erfahrener Imker, der für die Organisation von Bienenwanderungen und den Transport von Bienenvölkern verantwortlich ist. Er sorgt dafür, dass die Bienen zu den besten Nahrungsquellen geführt werden und trägt zur Gesundheit und Produktivität der Bienenvölker bei.")
          .image("152")
          .phone("+ 49 ")
          .facebook("https://www.facebook.com/")
          .instagram("https://www.instagram.com/")
          .email("Imkerverein-Ahlden@t-online.de")
          .build();
      membersRepository.save(newMember);
    }
  }
}

