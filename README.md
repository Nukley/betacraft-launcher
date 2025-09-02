# Betacraft95 TLS1.2 fork

## WARNING:
This fork of the launcher uses forks of older libraries from apache and google.

You will be putting yourself behind these security issues (CVEs):
## General security issues:
https://www.cve.org/CVERecord?id=CVE-2020-13956 (HttpClient 4.4 and HttpCore 4.4)
https://www.cve.org/CVERecord?id=CVE-2020-15250 (HttpClient 4.4, HttpCore 4.4, junit-4.8.2.jar and commons-codec-1.6.jar)

## commons-logging-1.2.jar (Is vulnerable to log4shell):
- https://www.cve.org/CVERecord?id=CVE-2022-23307
- https://www.cve.org/CVERecord?id=CVE-2022-23305
- https://www.cve.org/CVERecord?id=CVE-2022-23302
- https://www.cve.org/CVERecord?id=CVE-2021-4104
- https://www.cve.org/CVERecord?id=CVE-2019-17571

# By using my fork, you understand these risks, and if you get hacked it's your own doing.

## With that aside, here are some notes:
- This fork has horrible code, I wrote this a year ago while affecting my sleep for 3 days just to get good output from ChatGPT.
- My coding environment involved copying literal java source code, license violations, the likes.
- I have tried by best to clean up and seperate that nonsense in this release, but the ChatGPT 2024 code is still in here.

To be clear, I am not proud of using ChatGPT in the slightest, but it works, and I highly recommend using this only for reference.

## What is this fork?
- This is betacraft ported to Java 5, with Bouncycastle TLS 1.2 support.
 -It grabs a remote copy of https://github.com/Vulpovile/MiniJGL from my website.
- These modifications mean you can run this on Windows 95 using Java 5 update 5 and real microsoft authentication.

However, this also relies on forks of old libraries (because I'm lazy), so you will be behind a bunch of security vulnerabilities out of the box.

## Compiling?
- To make a working jar, you will need a version of eclipse that still supports Java 5, and a copy of the Java 5 JDK.

## Known issues:
- Classic online doesn't work
- Preclassic doesn't work
- Discord RPC has been dummied out
- Changelog page in the launcher is completely black and nearly unreadable

## FEATURES (some might be broken in my fork):
- Supports versions from Pre-Classic to 1.5.2:
  - skins & sound in versions that can handle them
  - starting Indev and early Infdev versions
  - mouse fix for Classic, Indev-Infdev versions on macOS
  - a1.1.1 gray screen fix
  - AMD clouds fix
  - fix for crash on `Mojang` screen before r1.3
  - multiplayer online-mode handling for pre-b1.8 versions
  - joining custom servers with the c0.0.15a version
  - resize game easily in versions that don't support resizing
  - can play every currently available legacy Minecraft version
- Microsoft sign in
- Mod repository, featuring great community mods
- Server list:
  - servers with live playercount and description
  - join servers by clicking on them
  - automatically downloads the mod a server uses if it's in mod repository
- Addons:
  - OfflineDATSave - allows for saving Classic levels on your disk (currently the only way to save in Classic)
  - Fullscreen - enables fullscreen mode for versions that don't officially have support for it
  - Demo - triggers demo mode for versions 12w16a and later
  - UnlicensedCopy - triggers `Unlicensed Copy :(` label for versions b1.6-tb3 to b1.7.3
  - QuitGame - shows the `Quit Game` button in versions b1.0 to 1.5.2
  - GameModeSwitch - switches to the opposite gamemode in versions c0.28_01 to inf-20100630-1835
  - ClassicNotPaid - displays `Premium only!` message when trying to save in any revision of c0.30
- Discord RPC
- Configurable:
  - JVM arguments
  - path to Java
  - instance directory
  - instance icon
  - starting resolution
- Console output
- BetaEvolutions support
- Supports many languages

## Supported platforms (v1):
- Windows 95+ (32/64 bit)
- any up-to-date Linux distro (64 bit)
- macOS 10.8+
### Note:
- We target Java 5
- Silicon Macs have inverted blue/red colors, for now you can only bypass this by going fullscreen on v1 (if you get Betacraft v2, the issue is fixed there)

## Reporting bugs or requesting features
Report bugs in [issues](https://github.com/betacraftuk/betacraft-launcher/issues).

## Contact:
- Website: https://betacraft.uk
- Bluesky: https://bsky.app/profile/betacraft.uk
- Mastodon: https://mastodon.social/@betacraft
- Telegram: https://t.me/betacraftukgroup
- Discord: https://discord.gg/d4WvXeQ
